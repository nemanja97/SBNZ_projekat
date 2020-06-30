import axios from "axios";

export const setupInterceptors = () => {
  axios.interceptors.request.use((config) => {
    config.headers["X-Auth-Token"] = `${localStorage.getItem("token")}`;
    return config;
  });

  axios.interceptors.response.use(
    (response) => {
      return response;
    },
    function (error) {
      return Promise.reject(error);
    }
  );
};
