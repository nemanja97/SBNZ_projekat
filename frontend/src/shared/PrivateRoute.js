import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { decodeToken, getToken } from '../services/TokenService';

export const PrivateRoute = ({ component: Component, roles, ...rest }) => (
    <Route {...rest} render={props => {
        const session = decodeToken(getToken())
        if (!session) {
            // not logged in so redirect to login page with the return url
            return <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
        }

        // check if route is restricted by role
        if (roles && !roles.includes(session.role)) {
            // role not authorised so redirect to home page
            return <Redirect to={{ pathname: '/home'}} />
        }

        // authorised so return component
        return <Component {...props} />
    }} />
)