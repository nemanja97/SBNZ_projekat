import React from "react";
import { Link } from "react-router-dom";

const RulesTable = (props) => {
  let i = 0;

  const ruleNames = (content) => {
    const regex = /(r|R)ule \".*\"/g;
    const names = content
      .match(regex)
      .map((r) => r.substring(r.indexOf('"') + 1, r.lastIndexOf('"')));
    return (
      <div className="content">
        <ol type="1">
          {names.map((name) => (
            <li>{name}</li>
          ))}
        </ol>
      </div>
    );
  };

  return (
    <>
      <table className="table is-fullwidth" style={{ minHeight: "650px", minWidth: "50vw" }}>
        <thead>
          <tr>
            <th>Path</th>
            <th>Content</th>
            <th>Review</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {props.rules &&
            props.rules.map((rule) => {
              return (
                <tr key={++i}>
                  <th>{rule.path}</th>
                  <td>{rule.content && ruleNames(rule.content)}</td>
                  <td>
                    <Link
                      className="button"
                      to={`/admin/rule?rule=${new URLSearchParams(rule.path).toString().substring(0, new URLSearchParams(rule.path).toString().length - 1)}`}
                    >
                      <span className="icon is-small">
                        <i class="fas fa-edit"></i>
                      </span>
                    </Link>
                  </td>
                  <td>
                    <button
                      className="button"
                      onClick={() => props.delete(`?rule=${new URLSearchParams(rule.path).toString().substring(0, new URLSearchParams(rule.path).toString().length - 1)}`, rule.path)}
                    >
                      <span className="icon is-small">
                        <i class="fas fa-backspace"></i>
                      </span>
                    </button>
                  </td>
                </tr>
              );
            })}
        </tbody>
      </table>
    </>
  );
};

export default RulesTable;
