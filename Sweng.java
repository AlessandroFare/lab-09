ADAPTER: converte l'interfaccia di una classe in una interfaccia diversa.

DECORATOR: consente di aggiungere metodi a classi esistenti durante il run-time (cioè durante lo svolgimento del programma), permettendo una maggior flessibilità nell'aggiungere delle funzionalità agli oggetti.

FACTORY:  fornisce un'interfaccia per creare un oggetto, ma lascia che le sottoclassi decidano quale oggetto istanziare

COMPOSITE: utilizzato per dare la possibilità all'utilizzatore di manipolare gli oggetti in modo uniforme, organizza gli oggetti in una struttura ad albero.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

OBSERVER: definisce una dipendenza uno a molti fra oggetti diversi, in maniera tale che se un oggetto cambia il suo stato, tutti gli oggetti dipendenti vengono notificati del cambiamento avvenuto e possono aggiornarsi.

STRATEGY: è utile in quelle situazioni dove è necessario modificare dinamicamente gli algoritmi utilizzati da un'applicazione.

BUILDER: separa la costruzione di un oggetto complesso dalla sua rappresentazione, in modo che il processo di costruzione stesso possa creare diverse rappresentazioni.


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1) ADAPTER

Below is the code of the Shape interface:

package design.adapter;
public interface Shape {
      void draw();
      void resize();
      String description();
      boolean isHide();
}


Below is the code of the concrete class, Rectangle:

package design.adapter;
public class Rectangle implements Shape {
      @Override
      public void draw() {
      System.out.println("Drawing Rectangle");
      }
      @Override
      public void resize() {
      System.out.println("Resizing Rectangle");
      }
      @Override
      public String description() {
      return "Rectangle object";
      }
      @Override
      public boolean isHide() {
      return false;
      }
}


Below is the code of the concrete class,  Circle:

package design.adapter;
public class Circle implements Shape {
      @Override
      public void draw() {
      System.out.println("Drawing Circle");
      }
      @Override
      public void resize() {
      System.out.println("Resizing Circle");
      }
      @Override
      public String description() {
      return "Circle object";
      }
      @Override
      public boolean isHide() {
      return false;
      }
}


Below is the code of the Drawing  class:

package design.adapter;
import java.util.ArrayList;
import java.util.List;
public class Drawing {
    List<Shape> shapes = new ArrayList<Shape>();
    public Drawing() {
    super();
    }
    public void addShape(Shape shape) {
    shapes.add(shape);
    }
    public List<Shape> getShapes() {
    return new ArrayList<Shape>(shapes);
    }
    public void draw() {
          if (shapes.isEmpty()) {
          System.out.println("Nothing to draw!");
          } else {
          shapes.stream().forEach(shape -> shape.draw());
          }
    }
    public void resize() {
          if (shapes.isEmpty()) {
          System.out.println("Nothing to resize!");
          } else {
          shapes.stream().forEach(shape -> shape.resize());
          }
    }
}


Below is the code of the Main class to execute and test the  Drawing.

package design.adapter;
public class Main {
      public static void main(String[] args) {
            System.out.println("Creating drawing of shapes...");
            Drawing drawing = new Drawing();
            drawing.addShape(new Rectangle());
            drawing.addShape(new Circle());
            System.out.println("Drawing...");
            drawing.draw();
            System.out.println("Resizing...");
            drawing.resize();
      }
}

FelineDomesticCat(class adapter) E FelinePelucheCat(object adapter) --> 

public FelinePelucheCat(PelucheCat cat) {
	this.cat=cat;
}

Entrambi implementano l'interfaccia Feline

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

2) DECORATOR

NOI ABBIAMO FATTO SOLO CON UN INTERFACCIA E UNA CLASSE...

Abbiamo l'interfaccia Feline e una classe Feline Counter  
che implementa l'interfaccia Feline e decora un'istanza di Feline in modo che venga conteggiato
il numero di versi emessi da una qualsiasi istanza decorata -->

package it.unimi.di.sweng.lecture;

public class FelineCounter implements Feline {

	private static int counter = 0;
	private final Feline feline;

	public FelineCounter(Feline feline) {
		this.feline = feline;
	}

	@Override
	public void roar() {
		feline.roar();
		counter++;
	}

	public static int getCount() {
		return counter;
	}

	public static void resetCount() {
		counter = 0;
	}
}


La struttura del decorator pattern si compone di quattro elementi principali:

Component: rappresenta l’interfaccia dell’oggetto che dovrà essere
decorato dinamicamente,

ConcreteComponent: rappresenta l’oggetto a cui andranno aggiunte
le nuove funzionalità,

Decorator: rappresenta l’interfaccia tra il Component e i ConcreteDecorator,
possiede un riferimento al Component e un’interfaccia a esso conforme,

ConcreteDecorator: rappresentano gli oggetti che aggiungono le
nuove funzionalità ai ConcreteComponent.



Component: Prima di tutto abbiamo bisogno di una prima classe astratta da cui deriveranno tutti i prodotti della nostra paninoteca: la classe Consumation modellerà la generica consumazione del cliente alla paninoteca Rossi, la quale sarà identificato da un nome del prodotto venduto e prezzo.

public abstract class Consumation {
 String productName = "";
 public String getProductName() {
 return productName;
 }
 public abstract double getPrice();
}



Decorator: Abbiamo poi bisogno della classa ExtraAdditionDecorator che modellerà ogni possibile aggiunta non prevista ad un prodotto: ad esempio un ingrediente aggiuntivo in un panino. Tale classe fa da classe base per i Decorator, ovvero modella tutti gli ingredienti aggiuntivi.

public abstract class ExtraAdditionDecorator extends Consumation {
 protected Consumation consumation;
 @Override
 public abstract String getProductName();
}



ConcreteComponent: Andiamo adesso a definire un po’ di entità del nostro dominio; ovvero dei prodotti venduti alla paninoteca Rossi. Nel nostro esempio vengono vendute due tipologie di panino:

public class CheeseBurger extends Consumation {
 public CheeseBurger() {
 productName = "CheeseBurger";
 }
 @Override
 public double getPrice() {
 return 2.50;
 }
}
public class Hamburger extends Consumation {
    public Hamburger() {
        productName = "Hamburger";
    }
    @Override
    public double getPrice() {
        return 2.00;
    }
}



ConcreteDecorator: Infine andiamo a definire un paio di decorator concreti; nel nostro esempio due tipi di ingredienti non previsti e compresi nei prezzi del menu: ExtraKetchupDecorator e ExtraMaioneseDecorator

public class ExtraMaioneseDecorator extends ExtraAdditionDecorator {
    public ExtraMaioneseDecorator(Consumation consumation){
        this.consumation = consumation;
    }
    @Override
    public String getProductName() {
        return consumation.getProductName()+ " con extra maionese";
    }
    @Override
    public double getPrice() {
        return consumation.getPrice()+0.20;
    }
}
public class ExtraKetchupDecorator extends ExtraAdditionDecorator {
    public ExtraKetchupDecorator(Consumation consumation){
        this.consumation = consumation;
    }
    @Override
    public String getProductName() {
        return consumation.getProductName()+ " con extra ketchup";
    }
    @Override
    public double getPrice() {
        return consumation.getPrice()+0.10;
    }
}



MAIN:Riportiamo adesso un semplice main:

public class Main {
 public static void main(String[] args) {
 //Hamburger
 Consumation hamburger = new Hamburger();
 System.out.println("Prodotto:" +
 hamburger.productName +
 " di prezzo " + String.format("%.2f", hamburger.getPrice()
 ));
 Consumation cheeseburger = new CheeseBurger();
 //voglio aggiungere la maionese al burger
 Consumation hamburgerConMaionese = new ExtraMaioneseDecorator(hamburger);
 System.out.println("Prodotto:" +
 hamburgerConMaionese.getProductName() +
 " di prezzo " + String.format("%.2f", hamburgerConMaionese.getPrice()));
 //voglio aggiungere la maionese e il ketchup al burger
 Consumation hamburgerConMaioneseKetchup = new ExtraKetchupDecorator(new ExtraMaioneseDecorator(hamburger));
 System.out.println("Prodotto:" +
 hamburgerConMaioneseKetchup.getProductName() +
 " di prezzo " + String.format("%.2f", hamburgerConMaioneseKetchup.getPrice()));
 Consumation cheeseburgerConMaionese = new ExtraMaioneseDecorator(cheeseburger);
 System.out.println("Prodotto:" +
 cheeseburgerConMaionese.getProductName() +
 " di prezzo " + String.format("%.2f", cheeseburgerConMaionese.getPrice()));
 }
}

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

3) FACTORY

NOI DOVEVAMO...Creare due diverse classi SimpleFelineFactory e CounterFelineFactory adibite rispettivamente 
alla creazione di semplici oggetti Feline e oggetti Feline incapsulati nel decorator introdotto al punto precedente. 
Tale obiettivo deve essere raggiunto usando il design pattern denominato abstract factory. 
Nello specifico le due factory concrete devono estendere la seguente classe astratta:

public abstract class AbstractFelineFactory {
  abstract public Feline createLion();
  abstract public Feline createTiger();
  abstract public Feline createDomesticCat();
  abstract public Feline createPelucheCat();
}

SimpleFelineFactory è cosi:

package it.unimi.di.sweng.lecture;

public class SimpleFelineFactory extends AbstractFelineFactory {

	@Override
	public Feline createLion() {
		return new Lion();
	}

	@Override
	public Feline createTiger() {
		return new Tiger();
	}
	
	@Override
	public Feline createDomesticCat() {
		return new FelineDomesticCat();
	}

	@Override
	public Feline createPelucheCat() {
		return new FelinePelucheCat(new PelucheCat());
	}
}

CounterFelineFactory è cosi:

package it.unimi.di.sweng.lecture;

public class CounterFelineFactory extends AbstractFelineFactory {

	@Override
	public Feline createLion() {
		return new FelineCounter(new Lion());
	}

	@Override
	public Feline createTiger() {
		return new FelineCounter(new Tiger());
	}
	
	@Override
	public Feline createDomesticCat() {
		return new FelineCounter(new FelineDomesticCat());
	}

	@Override
	public Feline createPelucheCat() {
		return new FelineCounter(new FelinePelucheCat(new PelucheCat()));
	}
}



In questo  pattern possiamo individuare i seguenti componenti:

Creator: dichiara la Factory che avrà il compito di ritornare l’oggetto appropriato

ConcreteCreator: effettua l’overwrite del metodo della Factory al fine di ritornare l’implementazione dell’oggetto

Product: definisce l’interfaccia dell’oggetto che deve essere creato dalla Factory

ConcreteProduct: implementa l’oggetto in base ai metodi definiti dall’interfaccia Product.



La Super Class nel factory method design pattern puo’ essere un interfaccia, classe astratta o una normale classe. Nel nostro caso prendiamo ad esempio l’interfaccia animal

public interface  Animal {
    String getCall();
}


Le classi implementeranno Animal dovranno fornire un implementazione del metodo getCall: metodo per ottenere il verso di un animale il quale sara’ differente per ogni specie.

public class Dog implements Animal {
    @Override
    public String getCall() {
        return "Bau";
    }
}


public class Cat implements Animal {
    @Override
    public String getCall() {
        return "Miao";
    }
}


Di seguito abbiamo l’implementazioni della Factory: la quale crea la specie giusta di animale in base all’enumerato specificato in input.

public enum AnimalEnum {
    Cat,
    Dog
}
 

public class AnimalFactory {
    public AnimalFactory(){}
    public Animal getAnimal (AnimalEnum type){
        Animal retval = null;
        switch (type){
            case Cat:
                retval = new Cat();
                break;
            case Dog:
                retval = new Dog();
                break;
        }
        return retval;
    }
}


Riportiamo un semplice main che per ogni valore dell’enumerato crea una specie dell’animale associato stampando a video il verso.

import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();
        Arrays.stream(
                AnimalEnum.values()).forEach(
                type-> System.out.println("Il verso e' "+ factory.getAnimal(type).getCall()));
    }
}

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

4) COMPOSITE

NOI DOVEVAMO...Creare una classe che rappresenti un branco di oggetti di tipo Feline.
Tale obiettivo deve essere raggiunto usando il design pattern denominato composite; 
più in dettaglio, è richiesta la realizzazione di una classe Gang che deve implementare 
l'interfaccia Feline e deve rispondere all'invocazione del metodo void roar() demandando 
la chiamata agli oggetti Feline aggregati:

import java.util.ArrayList;
import java.util.List;

public class Gang implements Feline {
	
	private List<Feline> felines = new ArrayList<Feline>();

	@Override
	public void roar() {
		for (Feline feline: felines)
			feline.roar();
	}

	public void add(Feline feline) {
		felines.add(feline);
	}

}


3.1. The Base Component
As a component object we’ll define a simple Department interface:

public interface Department {
    void printDepartmentName();
}


3.2. Leafs

For the leaf components, let’s define classes for financial and sales departments:

public class FinancialDepartment implements Department {
 
    private Integer id;
    private String name;
 
    public void printDepartmentName() {
        System.out.println(getClass().getSimpleName());
    }
 
    // standard constructor, getters, setters
}

The second leaf class, SalesDepartment, is similar:

public class SalesDepartment implements Department {
 
    private Integer id;
    private String name;
 
    public void printDepartmentName() {
        System.out.println(getClass().getSimpleName());
    }
 
    // standard constructor, getters, setters
}

Both classes implement the printDepartmentName() method from the base component, where they print the class names for each of them.

Also, as they’re leaf classes, they don’t contain other Department objects.

Next, let’s see a composite class as well.


3.3. The Composite Element

As a composite class, let’s create a HeadDepartment class:


public class HeadDepartment implements Department {
    private Integer id;
    private String name;
 
    private List<Department> childDepartments;
 
    public HeadDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.childDepartments = new ArrayList<>();
    }
 
    public void printDepartmentName() {
        childDepartments.forEach(Department::printDepartmentName);
    }
 
    public void addDepartment(Department department) {
        childDepartments.add(department);
    }
 
    public void removeDepartment(Department department) {
        childDepartments.remove(department);
    }
}

This is a composite class as it holds a collection of Department components, as well as methods for adding and removing elements from the list.

The composite printDepartmentName() method is implemented by iterating over the list of leaf elements and invoking the appropriate method for each one.


4. Testing
For testing purposes, let’s have a look at a CompositeDemo class:

public class CompositeDemo {
    public static void main(String args[]) {
        Department salesDepartment = new SalesDepartment(
          1, "Sales department");
        Department financialDepartment = new FinancialDepartment(
          2, "Financial department");
 
        HeadDepartment headDepartment = new HeadDepartment(
          3, "Head department");
 
        headDepartment.addDepartment(salesDepartment);
        headDepartment.addDepartment(financialDepartment);
 
        headDepartment.printDepartmentName();
    }
}
First, we create two instances for the financial and sales departments. After, we instantiate the head department and add up the previously created instances to it.

Finally, we can test the printDepartmentName() composition method. As we expect, the output contains the class names of each leaf component:

SalesDepartment
FinancialDepartment

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

5) OBSERVER

NOI DOVEVAMO CREARE...

Il decorator ObservableFeline che implementi l'interfaccia Feline ed estenda la classe Observable
e decori il Feline passato al suo costruttore in modo che l'invocazione del metodo roar possa essere osservata.


package it.unimi.di.sweng.lab07;

import it.unimi.di.sweng.lecture.Feline;

import java.util.Observable;

public class ObservableFeline extends Observable implements Feline {

    private Feline fel;
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer obser) {
        this.observers.add(obser);
    }

    @Override
    public void roar() {
        this.setChanged();
	//notifyObservers();
    }

    public ObservableFeline (Feline f) {
        this.fel=f;
    }

    public String getFeline(){
        return fel.getClass().getSimpleName();
    }

}


L'observer FelineLoggerObserver in modalità PULL che implementi l'interfaccia Observer ed emetta sullo standard error, 
per ogni invocazione di roar, il nome della classe sulla cui istanza osservata è stato invocato;


L'observer FelineCounterObserver in modalità PUSH che implementi l'interfaccia Observer ed abbia un metodo con signature 
public int getCount(final String name) che, dato un nome di classe come argomento, restituisca il numero di volte per cui roar 
è stato invocato su istanze osservate di tale classe e un metodo con signature public void resetCount() che azzeri tutti i conteggi;

Modalità push → il Subject invia informazioni
dettagliate sulla notifica
Modalità pull   →  il Subject non ivia alcuna informazione
Nel primo caso gli Observer sono meno riusabili

package it.unimi.di.sweng.lab07;

import java.util.Observer;

import java.util.Observable;

public class FelineLoggerObserver implements Observer{
    @Override
    public void update(Observable observable, Object o){ }

    public void update(ObservableFeline of, Object o){
        if (of.roar())
            System.err.println(of.getFeline()); //in modalità PULL perchè non dà alcuna informazione, ma solo un messaggio d'errore.
    }
}


package it.unimi.di.sweng.lab07;

import it.unimi.di.sweng.lecture.Feline;

import java.util.Observable;
import java.util.Observer;

public class FelineCounterObserver implements Observer{
    private int lioncount=0;
    private int tigercount=0;
    private int peluchecatcount=0;
    private int domesticcatcount=0;

    public void update(ObservableFeline observable, Object o) {

        if(observable.roar()) {
		increment(observable.getFeline());
        }
    }

    public int getCount(final String name){
        switch(name) {
            case "Lion" :
                return lioncount;

            case "Tiger" :
                return tigercount;

            case "FelinePelucheCat" :
                return peluchecatcount;

            case "FelineDomesticCat" :
                return domesticcatcount;

        }
        return 0;
    }

    public void increment(String name) {
        switch (name) {
            case "Lion":
                lioncount++;
                break;
            case "Tiger":
                tigercount++;
                break;
            case "FelinePelucheCat":
                peluchecatcount++;
                break;
            case "FelineDomesticCat":
                domesticcatcount++;
                break;
        }
    }


    @Override
    public void update(Observable observable, Object o) {

    }
}




Il pattern Observer trova applicazione nei casi in cui diversi oggetti (Observer) devono
conoscere lo stato di un oggetto (Subject). In poche parole  abbiamo un oggetto che viene “osservato” (il subject) e tanti oggetti che “osservano” i cambiamenti di quest’ultimo (gli observers). Analizziamo in dettaglio i componenti in gioco illustrati nel seguente diagramma UML.



Subject: classe Observable.
Ha conoscenza dei propri Observer, i quali possono essere in
numero illimitato
Fornisce operazioni per l’aggiunta e cancellazione di
Observer
Fornisce operazioni per la notifica agli Observer.

Observer: interfaccia Observer
Specifica una interfaccia per la notifica di eventi agli oggetti
interessati in un Subject.

ConcreteSubject: classe ObservedSubject.
mantiene lo stato del soggetto osservato e notifica gli observer in caso di un cambio di stato
 Invoca le operazioni di notifica ereditate dal Subject, quando
devono essere informati i ConcreteObserver.

ConcreteObserver:
implementa l’interfaccia dell’Observer definendo il comportamento in caso di cambio di stato del soggetto osservato
 

ESEMPIO
Ho creato per voi un semplice esempio che mostra una possibile implementazione di Observer Pattern. 
Vedremo come realizzare un sistema di notifiche per gli aggiornamenti di un risultato di un match sportivo. Nel nostro abbiamo :

Un match il cui risultato cambia nel tempo
N observer che vogliono abbonarsi a tale match per essere aggiornati sul risultato del match.
Mostriamo quindi il codice delle classi scritte per realizzare tale sistema.

public class ObservableMatch {
    private String matchScore;
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer channel) {
        //notifico il risultato iniziale non appena un observer si sottoscrive
        observer.update(this.matchScore);
        this.observers.add(channel);
    }
    public void removeObserver(Observer channel) {
        this.observers.remove(channel);
    }
    public ObservableMatch() {
        this.matchScore = "0-0";
    }
    public void setMatchScore(String newScore) {
        this.matchScore = newScore;
        for (Observer observer : this.observers) {
            observer.update(this.matchScore);
        }
    }
 ...
}


ObservableMatch è un observable: quando il matchScore è aggiornato con il relativo setter, ObservableMatch notificherà gli observer registrati a tale match chiamando il metodo update(). Per essere in grado di fare ciò, ObservableMatch ha bisogno di tenere il riferimento degli observer registrati, nel nostro caso con la lista observers. Abbiamo quindi a disposizione due metodi per gestire gli observer registrati al match:

addObserver: Metodo da invocare per registrare un observer. All’interno di tale metodo invochiamo  il metodo update per far ricevere all observer il risultato attuale del match, altrimenti quest’ultimo avrebbe dovuto attendere il primo aggiornamento del risultato per ricevere il risultato del match.

removeObserver: Rimuove la registrazione di un observer.
Vediamo adesso  l’interfaccia Observer e la sua implementazione.

public interface Observer {
    public void update(Object o);
}
public class ObserverMatch implements Observer {
    private String id;
    private String score;
    public ObserverMatch(String id) {
        this.id = id;
    }
    @Override
    public void update(Object score) {
        System.out.println("(observer-"+id+")risultato: "+ (String) score);
        this.score = (String) score;
    }
}

Il nostro observer riceve l’aggiornamento di un risultato di un match attraverso il metodo update(): il quale setta il campo score con l’ultimo score ricevuto e stampa infine a video il nuovo risultato del match. Per identificare le stampe a video abbiamo inserito un campo id per identificare i singoli observer.

A questo punto creiamo il nostro observable e due observer che si registrano per ricevere aggiornamenti sul risultato del match.

public static void main(String[] args) {
    ObservableMatch match = new ObservableMatch();
    Observer observer1 = new ObserverMatch("0");
    Observer observer2 = new ObserverMatch("1");
    match.addObserver(observer1);
    match.addObserver(observer2);
    match.setMatchScore("1-0");
    match.removeObserver(observer2);
    match.setMatchScore("2-0");
}

Se eseguiamo il main il programma stampa a video il seguente output:

(observer-0) risultato: 0-0
(observer-1) risultato: 0-0
(observer-0) risultato: 1-0
(observer-1) risultato: 1-0
(observer-0) risultato: 2-0

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

6) STRATEGY

Questo Design Pattern consiste nell’incapsulare un algoritmo all’interno di una classe, mantenendo un’interfaccia generica. 
Il tutto si traduce nel seguente diagramma delle classi.


Più nel dettaglio:

Strategy: dichiara l’interfaccia della nostra classe di algoritmi. Interfaccia che viene utilizzata da Context per invocare un algoritmo concreto

ConcreteStrategy (A e B) sono i nostri algoritmi concreti, ovvero implementano uno specifico algoritmo che espone l’interfaccia Strategy

Context, classe di contesto che invoca la ConcreteStrategy (sotto richiesta dei suoi client). Può esporre un’interfaccia, per consentire “all’algoritmo” di accedere ad una sua eventuale struttura dati interna.

Per rifarci al precedente esempio (la collezione di oggetti):

Context è la collezione stessa
Le strategie concrete potrebbero essere ad esempio BubbleSort, QuickSort, MergeSort, etc
Strategy sarà l’interfaccia comune a tutti i precedenti algoritmi di ordinamento.

UN ESEMPIO PRATICO
Cerchiamo di fare chiarezza con un semplice esempio: l’ordinamento di una collezione di dati.

Innanzitutto definiamo l’interfaccia SortStrategy, del nostro algoritmo, ovvero un unico metodo che si occuperà di eseguire il sort 
(e riceve come parametro l’array di elementi per riferimento).


interface SortStrategy
{
    void sort(ref int[] elements);
}

Procediamo quindi con la definizione di una classe per incapsulare un semplice algoritmo di ordinamento. In questo caso ho deciso di usare 
un bubble sort, andando quindi a definire la classe BubbleSortStrategy.


class BubbleSortStrategy : SortStrategy
{
    public void sort(ref int[] elements) {
        var size = elements.Length;
        bool swapped;
        do {
            swapped = false;
            for (int i = 0; i < size - 1; i++) {
                if (elements[i] > elements[i + 1]) {
                    this.swap(ref elements[i], ref elements[i + 1]);
                    swapped = true;
                }
            }
            size--;
        } while (swapped);
    }
    protected void swap(ref int a, ref int b) {
        var tmp = a;
        a = b;
        b = tmp;
    }
}


In questo contesto BubbleSortStrategy potrebbe anche contenere dei parametri di istanza 
(ad esempio un flag che stabilisce se l’ordinamento è ascendente o discendente). 
Per questo motivo non è difficile trovare lo Strategy Pattern in accoppiata con Factory Method o Abstract Factory.

Infine definiamo la nostra collezione ovvero la classe di contesto che dovrà utilizzare l’algoritmo di ordinamento 
(per brevità il codice di esempio implementa solo i metodi utili all’utilizzo dello Strategy Pattern).


class Collection
{
    protected int[] elements;
    protected SortStrategy sortStrategy;
    public Collection(int[] elements) {
        this.elements = elements;
        this.sortStrategy = null;
    }
    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }
    public void sort()
    {
        if (this.sortStrategy != null) {
            this.sortStrategy.sort(ref this.elements);
        }
    }
    public override String ToString()
    {
        return "[" + string.Join(
            ", ", 
            this.elements.Select(
                item => item.ToString()
            ).ToArray()
        ) + "]";
    }
    //...
}


La classe definisce due metodi per l’utilizzo dello strategy:

setSortStrategy, che consente di settare/modificare la strategia di sorting
sort, che invoca la strategia (quando settata), con i dovuti parametri
A questo punto possiamo testare il risultato:

MAIN:

var collection = new Collection(new int[]{ 1, 7, 6, 5, 4, 3, 2 });
collection.setSortStrategy(new BubbleSortStrategy());
collection.sort();
Console.WriteLine(collection);
Console.ReadKey();

Il codice di esempio dovrebbe essere abbastanza eloquente:

La prima riga crea un’istanza della collezione, con dei valori di default
L’invocazione del metodo setSortStrategy ci consente di settare una strategia di ordinamento (in questo caso l’istanza di BubbleSortStrategy)
L’invocazione del metodo sort ordina gli elementi presenti nella collezione (andando a sua volta ad invocare il metodo sort della strategia di ordinamento)

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

7) BUILDER

Builder pattern was introduced to solve some of the problems with Factory and Abstract Factory design patterns when the Object contains a lot of attributes.

There are three major issues with Factory and Abstract Factory design patterns when the Object contains a lot of attributes.

Too Many arguments to pass from client program to the Factory class that can be error prone because most of the time, the type of arguments are same and from client side its hard to maintain the order of the argument.

Some of the parameters might be optional but in Factory pattern, we are forced to send all the parameters and optional parameters need to send as NULL.
If the object is heavy and its creation is complex, then all that complexity will be part of Factory classes that is confusing.

We can solve the issues with large number of parameters by providing a constructor with required parameters and then different setter methods to set the optional parameters. The problem with this approach is that the Object state will be inconsistent until unless all the attributes are set explicitly.


Builder pattern solves the issue with large number of optional parameters and inconsistent state by providing a way to build the object step-by-step and provide a method that will actually return the final Object.


Builder Design Pattern in Java
Let’s see how we can implement builder design pattern in java.

First of all you need to create a static nested class and then copy all the arguments from the outer class to the Builder class. We should follow the naming convention and if the class name is Computer then builder class should be named as ComputerBuilder.
Java Builder class should have a public constructor with all the required attributes as parameters.
Java Builder class should have methods to set the optional parameters and it should return the same Builder object after setting the optional attribute.
The final step is to provide a build() method in the builder class that will return the Object needed by client program. For this we need to have a private constructor in the Class with Builder class as argument.
Here is the sample builder pattern example code where we have a Computer class and ComputerBuilder class to build it.



package com.journaldev.design.builder;

public class Computer {
	
	//required parameters
	private String HDD;
	private String RAM;
	
	//optional parameters
	private boolean isGraphicsCardEnabled;
	private boolean isBluetoothEnabled;
	

	public String getHDD() {
		return HDD;
	}

	public String getRAM() {
		return RAM;
	}

	public boolean isGraphicsCardEnabled() {
		return isGraphicsCardEnabled;
	}

	public boolean isBluetoothEnabled() {
		return isBluetoothEnabled;
	}
	
	private Computer(ComputerBuilder builder) {
		this.HDD=builder.HDD;
		this.RAM=builder.RAM;
		this.isGraphicsCardEnabled=builder.isGraphicsCardEnabled;
		this.isBluetoothEnabled=builder.isBluetoothEnabled;
	}
	
	//Builder Class
	public static class ComputerBuilder{

		// required parameters
		private String HDD;
		private String RAM;

		// optional parameters
		private boolean isGraphicsCardEnabled;
		private boolean isBluetoothEnabled;
		
		public ComputerBuilder(String hdd, String ram){
			this.HDD=hdd;
			this.RAM=ram;
		}

		public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
			this.isGraphicsCardEnabled = isGraphicsCardEnabled;
			return this;
		}

		public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
			this.isBluetoothEnabled = isBluetoothEnabled;
			return this;
		}
		
		public Computer build(){
			return new Computer(this);
		}

	}

}

Notice that Computer class has only getter methods and no public constructor. So the only way to get a Computer object is through the ComputerBuilder class.

Here is a builder pattern example test program showing how to use Builder class to get the object.

Copy

package com.journaldev.design.test;

import com.journaldev.design.builder.Computer;

public class TestBuilderPattern {

	public static void main(String[] args) {
		//Using builder to get the object in a single line of code and 
                //without any inconsistent state or arguments management issues		
		Computer comp = new Computer.ComputerBuilder(
				"500 GB", "2 GB").setBluetoothEnabled(true)
				.setGraphicsCardEnabled(true).build();
	}

}
