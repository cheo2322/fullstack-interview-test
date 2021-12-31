import BranchCommits from "../BranchCommits";

const Listable = ({ listOfObjects, credentials }) => {
  return (
    <ul>
      {listOfObjects.map((item) => (
        <li
          className="text-light mb-5"
          style={{ cursor: "pointer" }}
          key={item.name}
        >
          <div className="d-inline-flex">
            {/* {item.name} */}
            <BranchCommits branchName={item.name} credentials={credentials} />
          </div>
        </li>
      ))}
    </ul>
  );
};

export default Listable;
