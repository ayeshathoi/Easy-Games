import tkinter as tk
import random
import pygame as pg
pg.init()

passed = pg.mixer.Sound("passed.mp3")
fail = pg.mixer.Sound("wrong.mp3")
start = pg.mixer.Sound("start.mp3")
lose = pg.mixer.Sound("lose.mp3")

computer_guess = random.randint(1, 10)
count = 0

if count == 0:
    start.play()


def check():
    global count
    if txt_guess.get() == "":
        lbl_result["text"] = "Please enter a number!"
        return
    
    count += 1
    if count >= 3:
        lbl_result["text"] = "You lose!"
        lose.play()
        # reset the game
        return
    user_guess = int(txt_guess.get())
    if user_guess < computer_guess:
        msg = "Higher!"
        fail.play()    
    elif user_guess > computer_guess:
        msg = "Lower!"
        fail.play()
    elif user_guess == computer_guess:
        msg = "Correct!"
        passed.play()
    else:
        msg = "Something went wrong..."
    lbl_result["text"] = msg
    #clear text box for new guess
    txt_guess.delete(0, 5)
    #increment number of guesses
    

    

def reset():
    global computer_guess
    computer_guess = random.randint(1, 10)
    global count
    count = 0
    start.play()

    lbl_result["text"] = "Game reset. Guess again!"
    #clear text box for new guess
    txt_guess.delete(0, 5)
    #increment number of guesses
    count += 1

    

root = tk.Tk()
root.configure(bg="white")
root.title("Guess the number!")
root.geometry("300x200")

label = tk.Label(root, text="Guess the number in 3 tries!", bg="white")


label.pack()
lbl_result = tk.Label(root, text="Good Luck!", bg="white")
lbl_result.pack()

btn_check = tk.Button(root, text="Check",fg="green", bg="white", command=check)
btn_check.pack(side="left")
btn_reset = tk.Button(root, text="Reset",fg="red", command=reset, bg="white")
btn_reset.pack(side="right")

txt_guess = tk.Entry(root, width=3)
txt_guess.pack()




root.geometry()
root.mainloop()
root.destroy()