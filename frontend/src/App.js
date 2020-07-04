import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import { PrivateRoute } from "./shared/PrivateRoute";
import HomePage from "./public/Homepage";
import ResultsPage from "./public/Results";
import Dashboard from "./admin/Dashboard";
import Login from "./shared/Login";
import RulesDashboard from "./admin/RulesDashboard";
import Rule from "./admin/rules/Rule";
import 'react-widgets/dist/css/react-widgets.css';
import "./index.scss";
import Property from "./admin/Property";

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={Login} />
        <PrivateRoute
          exact
          path="/admin/dashboard"
          component={Dashboard}
          roles={["ROLE_ADMIN"]}
        />
        <PrivateRoute
          exact
          path="/admin/property"
          component={Property}
          roles={["ROLE_ADMIN"]}
        />
        <PrivateRoute
          exact
          path="/admin/property/:id"
          component={Property}
          roles={["ROLE_ADMIN"]}
        />
        <PrivateRoute
          exact
          path="/admin/rules"
          component={RulesDashboard}
          roles={["ROLE_ADMIN"]}
        />
        <PrivateRoute
          exact
          path="/admin/rule"
          component={Rule}
          roles={["ROLE_ADMIN"]}
        />
        <Route path="/home" component={HomePage} />
        <Route path="/results" component={ResultsPage} />
        <Route exact path="/">
          <Redirect to="/home" />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
