import random
import time
import pygame
pygame.init()
import turtle
turtle.speed(0) # Set turtle speed to the fastest

turtle.bgcolor("black") # Set background color to black
turtle.bgpic("starfield.gif") #600x600
turtle.setundobuffer(1) # Saves memory
turtle.ht() # Hide turtle
turtle.tracer(0) # Turn off screen updates
turtle.title("SpaceWar") # Set title to SpaceWar

fire_sound = pygame.mixer.Sound('./blaster.mp3')
explosion = pygame.mixer.Sound('./explosive.mp3') 
explosion.set_volume(0.5)


class Sprite(turtle.Turtle):
    def __init__(self, spriteshape, color, startx, starty):
        turtle.Turtle.__init__(self, shape = spriteshape)
        self.speed(0)
        self.penup() # Don't draw when moving
        self.color(color) 
        self.fd(0) # Move forward 0 pixels
        self.goto(startx, starty) # Go to startx and starty
        self.speed = 1 # Set speed to 1
    
    def move(self):
        self.fd(self.speed)
        if self.xcor() > 290 :
            self.setx(290)
            self.rt(60)
        if self.xcor() < -290 :
            self.setx(-290)
            self.rt(60)
        if self.ycor() < -290 :
            self.sety(-290)
            self.rt(60)
        if self.ycor() > 290 :
            self.sety(290)
            self.rt(60)
    
    def is_collision(self,other):
        if((self.xcor() - other.xcor() <=20)) and \
        ((self.ycor() - other.ycor() <=20)) and \
        ((self.xcor() - other.xcor() >=-20)) and \
        ((self.ycor() - other.ycor() >=-20)):
            return True
        else:
            return False

class Player(Sprite):
    def __init__(self, spriteshape, color, startx, starty):
        Sprite.__init__(self, spriteshape, color, startx, starty)
        self.shapesize(stretch_wid=0.6, stretch_len=1.1, outline=None)
        self.speed = 2

    
    def turn_left(self):
        self.lt(45) 
    def turn_right(self):
        self.rt(45)
    def accelerator(self):
        self.speed  +=1
    def decelerator(self):
        self.speed  -=1  

    

class Enemy(Sprite):
    def __init__(self, spriteshape, color, startx, starty):
        Sprite.__init__(self, spriteshape, color, startx, starty)
        self.speed = 6
        self.setheading(random.randint(0,360)) # Set random heading between 0 and 360

class Ally(Sprite):
    def __init__(self, spriteshape, color, startx, starty):
        Sprite.__init__(self, spriteshape, color, startx, starty)
        self.speed = 8
        self.setheading(random.randint(0,360)) # Set random heading between 0 and 360
    
    def move(self):
        self.fd(self.speed)
        if self.xcor() > 290 :
            self.setx(290)
            self.lt(60)
        if self.xcor() < -290 :
            self.setx(-290)
            self.lt(60)
        if self.ycor() < -290 :
            self.sety(-290)
            self.lt(60)
        if self.ycor() > 290 :
            self.sety(290)
            self.lt(60)


class Missile(Sprite):
    def __init__(self, spriteshape, color, startx, starty):
        Sprite.__init__(self, spriteshape, color, startx, starty)

        self.shapesize(stretch_wid=0.2, stretch_len=0.4, outline=None)
        self.speed = 20
        self.status = "ready"
        self.goto(-1000, 1000)
    
    def fire(self):
        fire_sound.play()
        if self.status == "ready":
            #os.system("aplay -d 1 blaster.mp3&")
            self.setheading(player.heading())
            self.goto(player.xcor(), player.ycor())
            self.status = "shoot"
    
    def move(self):

        if self.status == "ready":
            self.goto(-1000, 1000)

        if self.status == "shoot":
            self.fd(self.speed)
        # Border check
        if self.xcor() < -290 or self.xcor() > 290 or \
            self.ycor() < -290 or self.ycor() > 290:
            self.goto(-1000, 1000)
            self.status = "ready"

class Particle(Sprite):
    def __init__(self, spriteshape, color, startx, starty):
        Sprite.__init__(self, spriteshape, color, startx, starty)
        self.shapesize(stretch_wid=0.1, stretch_len=0.1, outline=None)
        self.goto(-1000, -1000)
        self.frame = 0 # Frame counter

    def explode(self,startx,starty):
        self.goto(startx,starty)
        self.setheading(random.randint(0,360))
        self.frame = 1
    
    def move(self):
        if self.frame > 0:
            self.fd(10)
            self.frame += 1

        if self.frame > 20:
            self.frame = 0
            self.goto(-1000, -1000)



class Game():
    def __init__(self):
        self.score = 0
        self.state = "Playing"
        self.live = 3
        self.pen = turtle.Turtle()

        
    def draw_border(self):
        self.pen.speed(0)
        self.pen.color("White")
        self.pen.pensize(3)
        self.pen.penup()
        self.pen.goto(-300, 300)
        self.pen.pendown()
        for side in range(4):
            self.pen.fd(600)
            self.pen.rt(90)
        self.pen.penup()
        self.pen.ht()


    def show_status(self):
        self.pen.undo()

        msg = "Score: %s" %(self.score) 
        life_status = "Life: %s" %(self.live)
        self.pen.penup()
        self.pen.goto(-290, 310)

        self.pen.write(msg, font=("Arial", 14, "normal"))
        self.pen.goto(200, 310)
        self.pen.write(life_status, font=("Arial", 14, "normal"))







game = Game()
game.draw_border()
game.show_status()
#enemy = Enemy("circle", "red", -100, 0)
player = Player("triangle", "white", 0, 0)
#ally = Ally("square", "blue", 100, 0)
missile = Missile("triangle", "yellow", 0, 0)
#keyboard bindings
turtle.onkey(player.turn_left,"Left")
turtle.onkey(player.turn_right,"Right")
turtle.onkey(player.accelerator,"Up")
turtle.onkey(player.decelerator,"Down")
turtle.onkey(missile.fire,"space")
turtle.listen()

particles = []
for i in range(20):
    particles.append(Particle("circle", "orange", 0, 0))


enemies = []
for i in range(6):
    enemies.append(Enemy("circle", "red", -100, 0))

allies = []
for i in range(6):
    allies.append(Ally("square", "blue", 100, 0))



while True:
    turtle.update()
    time.sleep(0.02) # Pause for 0.02 seconds
    player.move()
    #enemy.move()
    missile.move()

    for enemy in enemies:
        enemy.move()
   
        # Check for collision
        if player.is_collision(enemy):
            
            #play sound
            explosion.play()
            
            x = random.randint(-250, 250)
            y = random.randint(-250, 250)
            enemy.goto(x, y)
            game.score -= 50
            game.live -= 1
            game.pen.clear()
            game.show_status()
            game.draw_border()

               
                
  

            
        if missile.is_collision(enemy):
            #play sound
            
            x = random.randint(-250, 250)
            y = random.randint(-250, 250)
            enemy.goto(x, y)
            missile.status = "ready" # Reset missile
            game.score += 100
            game.show_status()
            # Do the explosion
            for particle in particles:
                particle.explode(missile.xcor(), missile.ycor())
                #particle.setheading(random.randint(0,360))



    for ally in allies:
        ally.move()
        if missile.is_collision(ally):
            #play sound
            explosion.play()
            x = random.randint(-250, 250)
            y = random.randint(-250, 250)
            ally.goto(x, y)
            missile.status = "ready"
    
    for particle in particles:
        particle.move()

    if game.live == 0:
        game.pen.goto(-100, 0)
        game.pen.write("Game Over", font=("Arial", 14, "normal"))
        missile.speed = 0
        for enemy in enemies:
            enemy.speed = 0
        for ally in allies:
            ally.speed = 0
        player.speed = 0
                
