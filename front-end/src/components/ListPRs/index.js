import axios from "axios";
import { useEffect, useState } from "react";
import PullRequestForm from "../PullRequestForm";

const ListPRs = () => {
  const [credentials, setCredentials] = useState({
    username: "",
    repositoryName: "",
    accessToken: "",
  });
  const [username, setUsername] = useState("");
  const [repositoryName, setRepositoryName] = useState("");
  const [accessToken, setAccessToken] = useState("");

  const [pullRequests, setPullRequests] = useState([]);

  useEffect(() => {
    if (credentials.username.length !== 0) {
      const uri = `http://localhost:8080/git-wrapper/pulls?user=${credentials.username}&repo=${credentials.repositoryName}`;
      const token = credentials.accessToken;

      axios
        .get(uri, {
          headers: {
            token,
          },
        })
        .then((res) => {
          const prs = res.data;
          setPullRequests(prs);
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
        <button
          className="text-light"
          className="btn btn-outline-primary"
          onClick={submit}
        >
          Buscar
        </button>
      </div>
      <ul style={{ columns: 2 }}>
        {pullRequests.map((pr) => (
          <li className="text-light mt-3" key={pr.number}>
            author: {pr.author}
            <br />
            title: {pr.title}
            <br />
            description: {pr.description}
            <br />
            status: {pr.status}
            <br />
          </li>
        ))}
      </ul>
      {credentials.username.length === 0 ? null : (
        <PullRequestForm credentials={credentials} />
      )}
    </div>
  );
};

export default ListPRs;
