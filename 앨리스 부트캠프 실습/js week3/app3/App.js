import "./app.css";
import BankAccount from "./BankAccount";

const App = () => {
  const nameInput = document.getElementById("name");
  const amountInput = document.getElementById("amount");

  const setNameButton = document.getElementById("set-name-button");
  const saveButton = document.getElementById("save-button");
  const withdrawButton = document.getElementById("withdraw-button");
  const result = document.getElementById("result");

  setNameButton.addEventListener("click", () => {
    BankAccount.changeName(nameInput.value);
    result.innerHTML = JSON.stringify(BankAccount, null, 2);
  });

  saveButton.addEventListener("click", () => {
    BankAccount.saveMoney(Number(amountInput.value));
    result.innerHTML = JSON.stringify(BankAccount, null, 2);
  });

  withdrawButton.addEventListener("click", () => {
    BankAccount.withdrawMoney(Number(amountInput.value));
    result.innerHTML = JSON.stringify(BankAccount, null, 2);
  });
};

export default App;
