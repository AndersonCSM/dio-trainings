
# Gerador é usado para códigos mais simples
# Iteradores é para estruturas mais complexas como árvores, grafos entre outros
def meu_gerador(numeros: list[int]):
    for numero in numeros:
        yield numero * 2


for i in meu_gerador(numeros=[1, 2, 3]):
    print(i)
