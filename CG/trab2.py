# Exemplos de call-backs do Matplotlib para a interação com o usuário
# Para terminar o programa, pressione a tecla 'a'

import numpy as np
import matplotlib.pyplot as plt

centro_x = 0
centro_y = 0
scale = 1.

# Função que gerencia eventos do mouse
def on_press(event):
    global centro_x
    global centro_y
    print('Você pressionou o botão do mouse:', event.button, event.xdata, event.ydata)
    centro_x = round(event.xdata,2)
    centro_y = round(event.ydata,2)
# Função que gerencia eventos do teclado
def on_key(event):
    print("Aqui")
    global end_loop
    global scale
    print('Você pressionou a tecla: "', event.key, '"', event.xdata, event.ydata)
    if event.key == 'q': 
        end_loop = True
    elif event.key == 'up':
        scale = scale + 0.05
    elif event.key == 'down':
        scale = scale - 0.05
    
plt.close('all')            # Fecha todas as figuras abertas
fig, ax = plt.subplots()    # Instancia objetos: figura e eixos correspondentes
ax.cla()                    # Limpa figura/eixos

# Conecta call backs associados aos eventos (mouse e teclado)
cid = fig.canvas.mpl_connect('button_press_event', on_press)
cid = fig.canvas.mpl_connect('key_press_event', on_key)

# Pontos de um quadrado unitário
po = np.array([[-0.5,-0.5,1],[0.5,-0.5,1],[0.5,0.5,1],[-0.5,0.5,1]])
po = np.transpose(po)

end_loop = False
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

    T = np.matmul(Tr,Ts)
    pT = np.matmul(T, po)
    plt.fill(pT[0,:], pT[1,:], facecolor=(1,0,0))
    plt.pause(0.01)


# Propriedades dos eixos da figura


plt.axhline(linewidth=1), plt.axvline(linewidth=1)

# Desenha pontos
plt.plot(po[0,:], po[1,:], 'ro')

# O loop acaba quando a tecla "q" é pressionada
#end_loop = False
#while not end_loop:
#    plt.pause(0.01)