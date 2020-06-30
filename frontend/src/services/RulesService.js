import axios from "axios";

export const RulesService = {
  getAll,
  get,
  remove,
  modifyRule,
  validateRule
};

async function getAll() {
  return await axios.get(`${process.env.REACT_APP_API_URL}/v1/rules`);
}

async function get(query) {
  return await axios.get(`${process.env.REACT_APP_API_URL}/v1/rules/rule${query}`);
}

async function remove(query) {
  return await axios.delete(`${process.env.REACT_APP_API_URL}/v1/rules/rule${query}`);
}

async function modifyRule(rule) {
  return await axios.post(
    `${process.env.REACT_APP_API_URL}/v1/rules`,
    rule
  );
}

async function validateRule(rule) {
  return await axios.post(
    `${process.env.REACT_APP_API_URL}/v1/rules/validate`,
    rule
  );
}