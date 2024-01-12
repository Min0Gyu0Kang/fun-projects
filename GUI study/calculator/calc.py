import tkinter as tk
from math import pow

class Calculator:
    def __init__(self, master):
        self.master = master
        self.master.title("Calculator")
        self.result_var = tk.StringVar()

        # Entry widget for displaying input/output
        entry = tk.Entry(master, textvariable=self.result_var, justify='right', font=('Cascadia', 20), bd=10, relief=tk.SOLID)
        entry.grid(row=0, column=0, columnspan=4, sticky='nsew')

        # Buttons
        buttons = [
            ('%', 1, 0,'grey'), ('^', 1, 1,'grey'), ('C', 1, 2,'grey'), ('Del', 1, 3,'grey'),
            ('7', 2, 0,'grey'), ('8', 2, 1,'grey'), ('9', 2, 2,'grey'), ('/', 2, 3,'grey'),
            ('4', 3, 0,'grey'), ('5', 3, 1,'grey'), ('6', 3, 2,'grey'), ('*', 3, 3,'grey'),
            ('1', 4, 0,'grey'), ('2', 4, 1,'grey'), ('3', 4, 2,'grey'), ('-', 4, 3,'grey'),
            ('0', 5, 0,'grey'), ('.', 5, 1,'grey'), ('=', 5, 2,'grey'), ('+', 5, 3,'grey'),
        ]

        for (text, row, col, color) in buttons:
            button = tk.Button(master, text=text, command=lambda t=text: self.button_click(t), height=2, width=5, bg=color, fg='white')
            button.grid(row=row, column=col, sticky='nsew')

         # Configure row and column weights
        for i in range(6):
            master.grid_rowconfigure(i, weight=1)
            master.grid_columnconfigure(i, weight=1)

        # Bind keyboard keys
        self.master.bind('<Return>', lambda event: self.button_click('='))
        self.master.bind('<BackSpace>', lambda event: self.button_click('Del'))
        self.master.bind('<Escape>', lambda event: self.button_click('C'))
        self.master.bind('<Key>', self.key_input)

    def key_input(self, event):
        key = event.char
        allowed_keys = '0123456789+-*/.^%='
        if key in allowed_keys:
            self.button_click(key)

    def button_click(self, text):
        current_input = self.result_var.get()


    def button_click(self, text):
        current_input = self.result_var.get()

        if text == 'C':
            self.result_var.set('')
        elif text == 'Del':
            self.result_var.set(current_input[:-1])
        elif text == '=':
            try:
                result = eval(current_input)
                self.result_var.set(result)
            except Exception as e:
                self.result_var.set('Error')
        else:
            self.result_var.set(current_input + text)

if __name__ == '__main__':
    root = tk.Tk()
    calculator = Calculator(root)
    root.mainloop()
