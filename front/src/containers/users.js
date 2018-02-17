import React, {Component} from "react";
import Axios from "axios";

class Users extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: "",
        };
    }

    handleSubmit(event) {
        event.preventDefault();
        this.refs.details.search(this.state.id);
        return;
    }

    render() {
        return (
            <div className="box">
                <form onSubmit={this.handleSubmit.bind(this)} autoComplete="off">
                    <input type="text" placeholder="customer_id"
                           onChange={(event) => this.setState({id: event.target.value})}/>
                    <input type="submit" value="Search"/>
                </form>
                <DetailsView ref="details"/>
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
            Axios.get(`/api/customers/${id}`).then((response) => {
                this.setState({customer: response.data, id: id});
            }).catch((response) => {
                //console.log(response)
            });
        }
    }

    render() {
        return (
            <div>
                {(() => {
                    if (this.state.id && this.state.customer) {
                        return (
                            <form>
                                <div className="form-group">
                                    <label className="form-label">customerId:</label>
                                    <label>{this.state.customer.id}</label>
                                </div>
                                <div className="form-group">
                                    <label className="form-label">firstName:</label>
                                    <label>{this.state.customer.firstName}</label>
                                </div>
                                <div className="form-group">
                                    <label className="form-label">lastName:</label>
                                    <label>{this.state.customer.lastName}</label>
                                </div>
                            </form>
                        );
                    } else if (this.state.id) {
                        return (
                            <span>NO such customer here</span>
                        );
                    }
                })()}
            </div>
        );
    }
}

export default Users;
