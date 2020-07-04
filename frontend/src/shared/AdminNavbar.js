import React from "react";
import { Link, useHistory } from "react-router-dom";
import { _logout } from "../services/AuthenticationService";

export default function AdminNavbar() {
  const history = useHistory();

  return (
    <nav className="navbar" role="navigation" aria-label="main navigation">
      <div className="navbar-brand">
        <Link className="navbar-item" to="/">
          <i className="fas fa-home"></i>
        </Link>
      </div>

      <div id="navbarBasicExample" className="navbar-menu">
        <div className="navbar-start">
          <Link className="navbar-item" to="/admin/dashboard">
            Dashboard
          </Link>
          <Link className="navbar-item" to="/admin/rules">
            Modify rules
          </Link>
          <Link className="navbar-item" to="/admin/reports">
            Reports
          </Link>
        </div>

        <div className="navbar-end">
          <div className="navbar-item">
            <div className="buttons">
              <Link
                className="button is-primary"
                onClick={() => {
                  _logout();
                  history.push("/home");
                }}
              >
                Log out
              </Link>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
}
