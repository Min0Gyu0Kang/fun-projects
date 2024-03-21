import "./app.css";
import Form from "./Form";

const App = () => {
  const favoriteForm = Form();

  favoriteForm.register("food", (value) => value.length > 1);
  favoriteForm.register("color", (value) => value.length > 1);

  const foodInput = document.getElementById("food");
  const colorInput = document.getElementById("color");
  const submitButton = document.getElementById("submit");
  const result = document.getElementById("result");

  foodInput.addEventListener("input", (e) => {
    favoriteForm.setValue("food", e.target.value);
  });

  colorInput.addEventListener("input", (e) => {
    favoriteForm.setValue("color", e.target.value);
  });

  submitButton.addEventListener("click", () => {
    const validationResult = favoriteForm.validate();
    if (!validationResult) {
      result.innerHTML = "입력된 값을 확인해주세요.";
      return;
    }
    result.innerHTML = "제출에 성공했습니다!";
  });
};

export default App;
