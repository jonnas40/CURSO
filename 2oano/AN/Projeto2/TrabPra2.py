import math


def epsilon():
    print("Epsilon maquina")
    eps = 1
    while eps + 1 > 1:
        eps = eps / 2
    return (eps)


def newton(F, Flinha, epsilon, x0):
    f = lambda x: eval(F)
    fl = lambda x: eval(Flinha)
    i = 0
    deltax = 1
    while deltax > epsilon:
        x1 = x0 - (f(x0) / fl(x0))
        deltax = math.fabs(x1-x0)
        i = i + 1
        x0 = x1
    print(x0)
    print("Numero de iteraçoes: ", i)


def raizes(F, Flinha, a, b, epsilon):
    x = a
    while x <= b:
        print(x)
        y = newton(F, Flinha, epsilon, x)
        # print("[", y - 0.05, "];" "[", y + 0.05, "]")
        x = x + 1


epsilon = epsilon()
print(epsilon)
f = "math.cos(x)+((1)/(1+math.e**(-2*x)))"
flinha = "((2* math.e**(2*x))/( ( (math.e**(2*x)) +1) **2) ) - math.sin(x)"
#func = newton(f, flinha, epsilon, 6)
raizes(f, flinha, -6.05, -5.95, epsilon)
