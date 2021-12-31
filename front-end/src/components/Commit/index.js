import { useState } from "react";

import axios from "axios";

const Commit = ({ commit, credentials }) => {
  const [fetchedcommit, setFetchedCommit] = useState({});

  const submit = () => {
    if (fetchedcommit.message) {
      setFetchedCommit({});
    } else {
      const uri = `http://localhost:8080/git-wrapper/commits/${commit.sha}?user=${credentials.username}&repo=${credentials.repositoryName}`;
      const token = credentials.accessToken;

      axios
        .get(uri, {
          headers: {
            token,
          },
        })
        .then((res) => {
          const commit = res.data;
          setFetchedCommit(commit);
        });
    }
  };

  return (
    <div className="d-inline-flex">
      <div onClick={submit}>{commit.message}</div>
      {fetchedcommit.message ? (
        <div className="m-3" onClick={submit}>
          message: {fetchedcommit.message}
          <br />
          timestamp: {fetchedcommit.timestamp}
          <br />
          filesChanged: ${fetchedcommit.filesChanged}
          <br />
          author: {fetchedcommit.author}
          <br />
          email: {fetchedcommit.email}
          <br />
        </div>
      ) : null}
    </div>
  );
};

export default Commit;
