import React, { Component} from 'react';
import BoardService from "../service/BoardService";
import axios from 'axios';
class Login extends Component {
    constructor(props){
        super(props);
        this.state = { 
            loginId:'',
            password:''
        }
    }

    clickLogin = (e) =>{
        let loginForm = {
            loginId : this.state.loginId,
            password : this.state.password
        }
        console.log(loginForm);
        BoardService.login(loginForm)
        .then(res => {
            axios.defaults.headers.common['Authorization'] = `Bearer ${res.data.accessToken}`;
            console.log(axios.defaults.headers)
            console.log(res.data.accessToken);
            this.props.history.push('/blog-posts');
        })
        .catch(error => {console.log(error)});
        e.preventDefault();
    }

    changeloginIdHandler = (event) => {
        this.setState({loginId: event.target.value});
      }
    
    changePasswordHandler = (event) => {
        this.setState({password: event.target.value});
    }
    
    render() {
        return (
            <form className="form-signin">
                <h2 className="form-signin-heading"> Please sign in </h2>
                <input id="inputLoginId" className="form-control" placeholder="loginId" onChange={this.changeloginIdHandler} />
                <input id="inputPassword" className="form-control" placeholder="Password" onChange={this.changePasswordHandler}/>
                <button onClick={this.clickLogin}> Sign in </button>
            </form>
        );
    }
}

export default Login;