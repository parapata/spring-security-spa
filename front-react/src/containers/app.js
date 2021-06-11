import React, {Component} from "react";
import Axios from "axios";
import Header from "../components/commons/header";
import Footer from "../components/commons/footer";
import Users from "./users";

const CTKN_KEY_NAME = "_ctkn";

const getCsrfToken = (cokie) => {
    let ret;
    if (document.cookie) {
        document.cookie.split(";").forEach(value => {
            let array = value.trim().split("=");
            if (CTKN_KEY_NAME === array[0]) {
                ret = array[1];
            }
        });
    }
    return ret;
};

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            csrf: "",
            user: [],
            id: ""
        };
    }

    componentDidMount() {
        Axios.get('/api/users').then((response) => {
            this.setState({user: response.data})
        }).catch((response) => {
            //console.log(response)
        });
        let token = getCsrfToken(document.cookie);
        if (token) {
            this.state.csrf = token;
        }
    }

    render() {
        return (
            <div>
                <Header userId={this.state.user.userId} csrf={this.state.csrf}/>
                <div>
                    <Users/>
                </div>
                <Footer/>
            </div>
        );
    }
}

export default App;
