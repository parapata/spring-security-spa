import React, {Component} from "react";
import axios from 'axios';

const CTKN_KEY_NAME = "_ctkn";

const getCsrfToken = (cokie) => {
    let ret;
    if(document.cookie){
        document.cookie.split(";").forEach(value => {
            let array = value.trim().split("=");
            if (CTKN_KEY_NAME === array[0]) {
                ret = array[1];
            }
        });
    }
    return ret;
};

export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            token: "",
            user: [],
            id: ""
        };
    }

    componentDidMount() {
        axios.get('/api/users').then((response) => {
            this.setState({user: response.data})
        }).catch((response) => {
            //console.log(response)
        });
        let token = getCsrfToken(document.cookie);
        if(token) {
            this.state.token = token;
        }
    }

    handleSubmit(event) {
        event.preventDefault();
        this.refs.details.search(this.state.id);
        return;
    }

    render() {
        return (
            <div>
                <header>
                    <form action="/logout" method="POST" autoComplete="off">
                        <input type="hidden" name="_csrf" value={this.state.token} />
                        <button type="submit">Logout</button>
\                    </form>
                    <div>user : {this.state.user.username}</div>
                </header>
                <div>
                    <form onSubmit={this.handleSubmit.bind(this)} autoComplete="off">
                        <input type="text" value={this.state.id} placeholder="customer_id"
                               onChange={(event) => this.setState({id: event.target.value})}/>
                        <input type="submit" value="Search"/>
                    </form>
                </div>
                <DetailsView ref="details"/>
                <footer></footer>
            </div>
        );
    }
}

class DetailsView extends Component {
    constructor() {
        super();
        this.state = {
            customer: [],
            id: ""
        };
    }

    search(id) {
        if (id) {
            axios.get(`/api/customers/${id}`).then((response) => {
                this.setState({customer: response.data})
            }).catch((response) => {
                //console.log(response)
            });
        }
        this.state.id = id;
    }

    render() {
        return (
            <div>
                {(() => {
                    if (this.state.customer) {
                        return (
                            <div>
                                <div>{this.state.customer.id}</div>
                                <div>{this.state.customer.firstName}</div>
                                <div>{this.state.customer.lastName}</div>
                            </div>
                        );
                    } else {
                        if (this.state.id) {
                            return <span>NO such customer here</span>
                        }
                    }
                })()}
            </div>
        );
    }
}

