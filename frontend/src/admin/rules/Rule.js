import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import BasicRuleEdit from "./BasicRuleEdit";
import AdminNavbar from "../../shared/AdminNavbar";
import { RulesService } from "../../services/RulesService";

function Rule() {
  const [rule, setRule] = useState();
  const [validateMessage, setValidateMessage] = useState(undefined);

  const location = useLocation();

  // ****************************************************************************************************
  // Initial data
  // ****************************************************************************************************

  useEffect(() => {
    setRule(location.rule);
  }, []);

  // ****************************************************************************************************
  // Value change handling
  // ****************************************************************************************************

  const handleFormInputChange = (name) => (event) => {
    const val = event.target.value;
    setRule({ ...rule, [name]: val });
  };

  // ****************************************************************************************************
  // API handling
  // ****************************************************************************************************

  const handleSave = async () => {
    /*     await RuleService.updateRules(rule)
      .then((res) => setValidateMessage("Validation succesful.\nRule updated"))
      .catch(function (error) {
        if (error.response) {
          let errorMessages = "Validation failed";
          const data = error.response.data;
          console.log(data);

          data.map((err) => (errorMessages += "\n" + err.text));

          console.log(errorMessages);
          setValidateMessage(errorMessages);
        }
      }); */
  };

  const handleValidate = async () => {
    RulesService.validateRule(rule)
      .then((res) => setValidateMessage("Validation succesful."))
      .catch(function (error) {
        if (error.response) {
          let errorMessages = "Validation failed";
          const data = error.response.data;
          console.log(data);

          data.map((err) => (errorMessages += "\n" + err.text));

          console.log(errorMessages);
          setValidateMessage(errorMessages);
        }
      });
  };

  return (
    <>
      <AdminNavbar />
      {rule && (
        <BasicRuleEdit
          rule={rule}
          validateMessage={validateMessage}
          inputChange={handleFormInputChange}
          save={handleSave}
          validate={handleValidate}
        />
      )}
    </>
  );
}

export default Rule;
