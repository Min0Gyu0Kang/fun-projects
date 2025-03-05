const BankAccount = {
  deposit: 0,
  name: "",

  changeName: function (name) {
    // BankAccount의 이름을 바꾸세요.
    this.name=name;
  },

  saveMoney: function (amount) {
    // amount를 deposit에 더합니다.
    this.deposit+=amount;
  },

  withdrawMoney: function (amount) {
    // amount를 deposit에서 뺍니다.
    this.deposit-=amount;
  },

  getDeposit: function () {
    // deposit을 리턴합니다.
    return this.deposit;
  },
};

export default BankAccount;
