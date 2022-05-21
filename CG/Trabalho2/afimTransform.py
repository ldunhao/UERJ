import numpy as np
import matplotlib.pyplot as plt
from skimage import color

def initialize():
    global fig, ax, end_loop

    plt.close('all')   
    fig, ax = plt.subplots()
    ax.cla()
    end_loop = False

    cid = fig.canvas.mpl_connect('button_press_event', on_press)
    cid = fig.canvas.mpl_connect('key_press_event', on_key)

def on_press(event):
    global centro_x
    global centro_y
    print('Você pressionou o botão do mouse:', event.button, event.xdata, event.ydata)
    centro_x = round(event.xdata,2)
    centro_y = round(event.ydata,2)

def on_key(event):
    global end_loop
    global scale
    global rotVel
    print('Você pressionou a tecla: "', event.key, '"', event.xdata, event.ydata)
    if event.key == 'q': 
        end_loop = True
    elif event.key == 'up':
        scale = scale + 0.05
    elif event.key == 'down':
        scale = scale - 0.05
    elif event.key == 'left':
        rotVel = rotVel + 1
    elif event.key == 'right':
        rotVel = rotVel - 1

def startAfimTransformation(fig):
    po = np.transpose(fig)
    initialize()

    global centro_x, centro_y, scale, delta, rotVel

    centro_x = 0
    centro_y = 0
    scale = 1.
    delta = 0
    rotVel = 0

    while not end_loop:
        ax.cla()
        ax.set_xlim([-7,7]), ax.set_ylim([-7,7])
        ax.set_aspect(1)

        Tr = np.array([
            [1,0,centro_x],
            [0,1,centro_y],
            [0,0,1]
        ])

        Ts = np.array([
            [scale,0,0],
            [0,scale,1],
            [0,0,1]
        ])

        delta_rad = (delta / 180) * np.pi
                            
        Rot = np.array([[np.cos(delta_rad), -np.sin(delta_rad), 0],
                        [np.sin(delta_rad), np.cos(delta_rad), 0],
                        [0, 0, 1]])

        T = np.matmul(Tr,Ts)
        Mr = np.matmul(T,Rot)
        pT = np.matmul(Mr, po)

        colorHSV = color.hsv2rgb([delta/360,1,1])
        
        plt.fill(pT[0,:], pT[1,:], facecolor=(colorHSV))
        plt.pause(0.01)

        delta = (delta + rotVel) % 360