import { useState } from "react";
import Commit from "../Commit";

import axios from "axios";

const BranchCommits = ({ branchName, credentials }) => {
  const [commits, setCommits] = useState([]);

  const submit = () => {
    if (commits.length === 0) {
      const uri = `http://localhost:8080/git-wrapper/commits?branch=${branchName}&user=${credentials.username}&repo=${credentials.repositoryName}`;
      const token = credentials.accessToken;

      axios
        .get(uri, {
          headers: {
            token,
          },
        })
        .then((res) => {
          const fetchedCommits = res.data;
          setCommits(fetchedCommits);
        });
    } else {
      setCommits([]);
    }
  };

  return (
    <div className="d-inline-flex">
      <div onClick={submit}>{branchName}</div>
      <ul>
        {commits.map((commit) => (
          <li key={commit.sha}>
            <div className="d-inline-flex">
              <Commit
                key={commit.sha}
                commit={commit}
                credentials={credentials}
              />
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BranchCommits;
