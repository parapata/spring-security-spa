import React, {Component} from "react";
import axios from 'axios';

export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {user: []};
    }

    componentDidMount() {
        axios.get('/api/users').then((response) => {
            this.setState({user: response.data})
        }).catch((response) => {
            //console.log(response)
        });
    }

    render() {
        return (
            <div className="App">
                <div>user : {this.state.user.username}</div>
            </div>
        );
    }
}
