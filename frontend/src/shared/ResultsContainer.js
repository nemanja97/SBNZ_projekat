import React, { useState } from "react";
import Result from "../shared/Result";

export default function ResultsContainer(props) {
  const handlePageClick = (value) => {
    props.handlePageChange({
      value,
    });
  };

  return (
    <>
      {props.properties &&
        props.properties.map((p, idx) => {
          if (props.page.value === idx)
            return <Result property={p} key={p.id} />;
        })}
      <nav
        className="pagination is-rounded is-centered is-medium"
        role="navigation"
        aria-label="pagination"
      >
        <ul className="pagination-list">
          {props.properties &&
            props.properties.map((_, idx) => {
              if (idx === props.page.value) {
                if (idx - 1 >= 0 && idx + 1 !== props.properties.length) {
                  return (
                    <>
                      <li>
                        <a
                          className="pagination-link"
                          onClick={() => handlePageClick(idx - 1)}
                        >
                          {idx}
                        </a>
                      </li>
                      <li>
                        <a
                          className="pagination-link is-primary"
                          onClick={() => handlePageClick(idx)}
                        >
                          {idx + 1}
                        </a>
                      </li>
                      <li>
                        <a
                          className="pagination-link"
                          onClick={() => handlePageClick(idx + 1)}
                        >
                          {idx + 2}
                        </a>
                      </li>
                    </>
                  );
                }
                if (idx - 1 >= 0) {
                  return (
                    <>
                      <li>
                        <a
                          className="pagination-link"
                          onClick={() => handlePageClick(idx - 1)}
                        >
                          {idx}
                        </a>
                      </li>
                      <li>
                        <a
                          className="pagination-link is-primary"
                          onClick={() => handlePageClick(idx)}
                        >
                          {idx + 1}
                        </a>
                      </li>
                    </>
                  );
                }
                if (idx + 1 !== props.properties.length) {
                  return (
                    <>
                      <li>
                        <a
                          className="pagination-link is-primary"
                          onClick={() => handlePageClick(idx)}
                        >
                          {idx + 1}
                        </a>
                      </li>
                      <li>
                        <a
                          className="pagination-link"
                          onClick={() => handlePageClick(idx + 1)}
                        >
                          {idx + 2}
                        </a>
                      </li>
                    </>
                  );
                }
              }
            })}
        </ul>
      </nav>
    </>
  );
}
