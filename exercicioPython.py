ListaPessoa = []
for i in range(3):
    nome = input("qual o seu nome ?")
    idade = int(input("Qual a sua idade ?"))
    
    ListaPessoa.append([nome,idade])

idadeSoma = 0
for i in ListaPessoa:
    print(f"Olá, {i[0]}! Você tem {i[1]} anos.")
    idadeSoma +=i[1] 
idadeMedia = idadeSoma/3
print(f"As Médias das idade é:{idadeSoma/3}")