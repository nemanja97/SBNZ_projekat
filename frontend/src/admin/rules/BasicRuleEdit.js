import React from "react";

const BasicRuleEdit = (props) => {
  return (
    <>
      <div className="container is-fluid">
        <div className="columns is-mobile">
          <div className="column is-9">
            <div className="control">
              <input
                className="input"
                type="text"
                value={props.rule.path}
                placeholder="Rule name"
                style={{ marginBottom: "10px" }}
                onChange={props.inputChange("path")}
              />
              <textarea
                className="textarea has-fixed-size"
                rows="20"
                onChange={props.inputChange("content")}
                value={props.rule.content}
              ></textarea>
            </div>
          </div>
          <div className="column is-3">
            <textarea
              style={{ marginTop: "50px" }}
              className="textarea has-fixed-size"
              readonly
              rows="10"
              placeholder="Validation output"
              value={props.validateMessage}
            />
            <div className="control">
              <button
                className="button is-fullwidth is-primary"
                style={{ marginTop: "5px", marginBottom: "5px" }}
                onClick={() => props.save()}
              >
                Save
              </button>
            </div>
            <div className="control">
              <button
                className="button is-fullwidth is-info"
                onClick={() => props.validate()}
              >
                Validate
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default BasicRuleEdit;
