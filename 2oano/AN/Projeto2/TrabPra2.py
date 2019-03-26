import math

def newton(F, Flinha, epsilon, x0):
    f = lambda x: eval(F)
    fl = lambda x: eval(Flinha)
    i = 0
    deltax = math.fabs(x0)
    while deltax > epsilon:
        x1 = x0 - (f(x0) / fl(x0))
        deltax = math.fabs(x1-x0)
        i = i + 1
        x0 = x1
    print(x0)
    print("Numero de iteraçoes: ", i)
    return x0



def iterativo(G, Glinha, epsilon, x0):
    g = lambda x: eval(G)
    gl = lambda x: eval(Glinha)
    i = 0
    deltax = math.fabs(x0)
    if math.fabs(gl(x0))>=1:       #verifica se a funcao diverge
        print("Funcão Diverge")
        print(gl(x0))
        return
    while deltax > epsilon:         #enquanto o erro for menor que epsilon continua a iterar
        x1 = g(x0)
        deltax = math.fabs(x1-x0)
        i = i + 1
        x0 = x1
    print(x0)
    print("Numero de iteraçoes: ", i)
    return x0


f = "math.cos(x)+((1)/(1+math.e**-(2*x)))"
flinha = "(2* math.e**(2*x))/((1+math.e**(2*x))**2) - math.sin(x)"
g = "math.acos(-(1/(1+math.e**(-2*x))))"
glinha = "(2*math.e**(2*x))/((math.e**(2*x)+1)**2*math.sqrt(1-(1/(1+math.e**(-2*x))**2)))"
func = newton(f, flinha, 5*10**(-14), 3.01)
iterativo(g, glinha, 5*10**(-14), 3.01)
