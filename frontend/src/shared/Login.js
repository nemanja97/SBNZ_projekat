import React, { useState } from "react";
import "react-bulma-components/dist/react-bulma-components.min.css";
import { useHistory } from "react-router-dom";

function Login() {
  const [user, setUser] = useState({
    email: "",
    password: "",
  });
  const history = useHistory();

  const handleChange = (name) => (event) => {
    const value = event.target.value;
    setUser({ ...user, [name]: value });
  };

  const handleLogin = (event) => {
    event.preventDefault();
    console.log(user);
  };

  return (
    <div>
      <section className="section hero is-fullheight">
        <div className="hero-body">
          <div className="container">
            <div className="columns is-centered">
              <div className="column is-5-tablet is-4-desktop is-3-widescreen">
                <div className="box">
                  <div className="field">
                    <label className="label">Email</label>
                    <div className="control has-icons-left">
                      <input
                        className="input"
                        type="email"
                        placeholder="e.g user@mail.com"
                        onChange={handleChange("email")}
                      />
                      <span className="icon is-small is-left">
                        <i className="fas fa-envelope"></i>
                      </span>
                    </div>
                  </div>
                  <div className="field">
                    <label className="label">Password</label>
                    <div className="control has-icons-left">
                      <input
                        className="input"
                        type="password"
                        placeholder="*******"
                        onChange={handleChange("password")}
                      />
                      <span className="icon is-small is-left">
                        <i className="fas fa-lock"></i>
                      </span>
                    </div>
                  </div>
                  <div className="field">
                    <div className="control">
                      <button 
                        className="button is-link"
                        onClick={handleLogin}>Login</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Login;
