import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import { PrivateRoute } from './shared/PrivateRoute';
import HomePage from './public/Homepage';
import ResultsPage from './public/Results';
import Dashboard from './admin/Dashboard';
import Login from './shared/Login';

function App() {
  return (
    <Router>
      <Switch>
        <Route path='/login' component={Login}/>
        <PrivateRoute exact path='/admin/dashboard' component={Dashboard}/>
        <Route path='/home' component={HomePage}/>
        <Route path='/results' component={ResultsPage}/>
        <Route exact path="/">
            <Redirect to="/home"/>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
