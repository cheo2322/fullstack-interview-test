import { useEffect, useState } from "react";
import Listable from "../../components/Listable";

import axios from "axios";

const Repository = () => {
  const [credentials, setCredentials] = useState({
    username: "",
    repositoryName: "",
    accessToken: "",
  });
  const [username, setUsername] = useState("");
  const [repositoryName, setRepositoryName] = useState("");
  const [accessToken, setAccessToken] = useState("");

  const [branches, setBranches] = useState([]);

  useEffect(() => {
    if (credentials.username.length !== 0) {
      const uri = `http://localhost:8080/git-wrapper/branches?user=${credentials.username}&repo=${credentials.repositoryName}`;
      const token = credentials.accessToken;

      axios
        .get(uri, {
          headers: {
            token,
          },
        })
        .then((res) => {
          const branches = res.data;
          setBranches(branches);
        });
    }
  }, [credentials]);

  const submit = () => {
    setCredentials({ username, repositoryName, accessToken });
  };

  const onChangeUsername = (e) => {
    setUsername(e.target.value);
  };

  const onChangeRepository = (e) => {
    setRepositoryName(e.target.value);
  };

  const onChangeToken = (e) => {
    setAccessToken(e.target.value);
  };

  return (
    <div className="d-inline-flex justify-content-center p-5 mh-100 mw-100">
      <div className="d-inline-flex flex-column">
        <input
          className="form-control me-2 mb-3"
          type="username"
          placeholder="Username"
          aria-label="Username"
          value={username}
          onChange={onChangeUsername}
        />
        <input
          className="form-control me-2 mb-3"
          type="repo"
          placeholder="Repository Name"
          aria-label="Repo"
          value={repositoryName}
          onChange={onChangeRepository}
        />
        <input
          className="form-control me-2  mb-3"
          type="token"
          placeholder="Access Token"
          aria-label="Token"
          value={accessToken}
          onChange={onChangeToken}
        />
        <button className="btn btn-outline-primary" onClick={submit}>
          Buscar
        </button>
      </div>
      <Listable listOfObjects={branches} credentials={credentials}></Listable>
    </div>
  );
};

export default Repository;
