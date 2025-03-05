// import "./app.css";
import NameRepeater from "./NameRepeater";

const App = () => {
  const input = document.getElementById("name");
  const setButton = document.getElementById("set-button");
  const repeatButton = document.getElementById("repeat-button");
  const result = document.getElementById("result");
  let cnt = 2;

  setButton.addEventListener("click", () => {
    NameRepeater.setName(input.value);
    result.innerHTML = "설정되었습니다.";
  });

  repeatButton.addEventListener("click", () => {
    const resultString = NameRepeater.repeatName(cnt);
    cnt++;
    result.innerHTML = resultString;
  });
};

export default App;
