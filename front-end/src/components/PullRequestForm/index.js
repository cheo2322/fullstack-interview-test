import axios from "axios";
import { useAlert } from "react-alert";
import { useEffect, useState } from "react";

const PullRequestForm = ({ credentials }) => {
  const alert = useAlert();

  const [payload, setPayload] = useState({
    title: "",
    body: "",
    head: "",
    base: "",
  });

  useEffect(() => {
    if (payload.title.length !== 0 && credentials.username.length !== 0) {
      const uri = `http://localhost:8080/git-wrapper/pull?user=${credentials.username}&repo=${credentials.repositoryName}`;
      const token = credentials.accessToken;

      axios
        .post(uri, payload, {
          headers: {
            token,
          },
        })
        .then((res) => {
          if (payload.status === "OPEN") {
            alert.success("You created a Pull Request!");
          } else {
            alert.success("You have Merged a Pull Request!");
          }
          setTitle("");
          setBody("");
          setHead("");
          setBase("");
        })
        .catch((err) => {
          alert.error("Opps, there was an error, try again please...");
        });
    }
  }, [credentials, payload]);

  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const [head, setHead] = useState("");
  const [base, setBase] = useState("");

  const onChangeTitle = (e) => {
    setTitle(e.target.value);
  };

  const onChangeBody = (e) => {
    setBody(e.target.value);
  };

  const onChangeHead = (e) => {
    setHead(e.target.value);
  };

  const onChangeBase = (e) => {
    setBase(e.target.value);
  };

  const create = () => {
    setPayload({ title, body, head, base, status: "OPEN" });
  };
  const merge = () => {
    setPayload({ title, body, head, base, status: "MERGED" });
  };

  return (
    <div className="d-inline-flex flex-column">
      <input
        className="form-control me-2 mb-3"
        type="text"
        placeholder="Title"
        aria-label="Title"
        value={title}
        onChange={onChangeTitle}
      />
      <input
        className="form-control me-2  mb-3"
        type="text"
        placeholder="Body"
        aria-label="Body"
        value={body}
        onChange={onChangeBody}
      />
      <input
        className="form-control me-2 mb-3"
        type="text"
        placeholder="Head"
        aria-label="Head"
        value={head}
        onChange={onChangeHead}
      />
      <input
        className="form-control me-2  mb-3"
        type="text"
        placeholder="Base"
        aria-label="Base"
        value={base}
        onChange={onChangeBase}
      />
      <button className="text-light btn btn-primary" onClick={create}>
        Create
      </button>
      <button className="text-light btn btn-info" onClick={merge}>
        Merge
      </button>
    </div>
  );
};

export default PullRequestForm;
