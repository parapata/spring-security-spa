import React, {Component} from "react";

class Header extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <header>
                <form action="/logout" method="POST" autoComplete="off">
                    <input type="hidden" name="_csrf" value={this.props.csrf}/>
                    <button type="submit">Logout</button>
                </form>
                <div>user:{this.props.userId}</div>
            </header>
        );
    }
}

export default Header;
