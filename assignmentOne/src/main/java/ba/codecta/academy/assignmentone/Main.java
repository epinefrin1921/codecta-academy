package ba.codecta.academy.assignmentone;

import ba.codecta.academy.assignmentone.characters.DisneyCharacter;
import ba.codecta.academy.assignmentone.characters.DummyCharacter;
import ba.codecta.academy.assignmentone.characters.MickeyCharacter;
import ba.codecta.academy.assignmentone.characters.MinnieCharacter;
import ba.codecta.academy.assignmentone.disneylands.AdventureLand;
import ba.codecta.academy.assignmentone.disneylands.FantasyLand;
import ba.codecta.academy.assignmentone.disneylands.IDisneyland;
import ba.codecta.academy.assignmentone.disneylands.MickeyTown;
import ba.codecta.academy.assignmentone.netflix.Movie;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static String name = "";
    private static List<String> zanroviZaIzbjeci;
    public static void main(String[] args) throws ParseException {

        logger.trace("Entering application.");
        String tema="";
        zanroviZaIzbjeci = new ArrayList<>();


        if( args.length>0){
            Options options = new Options();

            options.addOption("w",  true, "Choose world (disney or movie).")
                    .addOption("n", true, "Enter name");
            Option optionGenres = Option.builder("g")
                    .hasArg()
                    .build();
            optionGenres.setArgs(Option.UNLIMITED_VALUES);
            options.addOption(optionGenres);

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse( options, args);

            if(cmd.hasOption("w")){
                tema = cmd.getOptionValue("w");
            }
            if(cmd.hasOption("n")){
                name=cmd.getOptionValue("n");
            } else {
                System.out.println("Welcome, guest! What is Your name?");
                name = scanner.nextLine();
            }
            if(cmd.hasOption("g")){
                String[] genres = cmd.getOptionValues("g");
                zanroviZaIzbjeci.addAll(Arrays.asList(genres));
            }
        } else {
            System.out.println("Welcome, guest! What is Your name?");
            name = scanner.nextLine();
             boolean isSet=false;
             do {
                System.out.println("Welcome to my app, " + name +"!");
                System.out.println("Please choose Your world: ");
                System.out.println("1 - Disney World");
                System.out.println("2 - Movie World");
                System.out.println("3 - to exit");

                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option){
                    case 1:
                        tema = "disney";
                        isSet=true;
                        break;
                    case 2:
                        isSet=true;
                        tema="movie";
                        break;
                    case 3:
                        isSet=true;
                        tema="exit";
                        break;
                    default:
                        System.out.println("Please enter valid option");
                        break;
                }

            } while (!isSet);

        }
        boolean isActive = true;

        do{
            if(tema.equals("disney")){
                goToDisneyWorld();
            } else if(tema.equals("movie")){
                goToMovieWorld();
            }
            else{
                System.out.println("There is no world with that option");
                System.out.println("Please try again or press 3 to quit");
            }

            System.out.println();
            System.out.println("***************************");

            System.out.println("Please enter Your selection: ");
            System.out.println("1 - Go to DisneyWorld");
            System.out.println("2- Go to MovieWorld");
            System.out.println("3 - to quit the app");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    tema="disney";
                    break;
                case 2:
                    tema="movie";
                    break;
                case 3:
                    isActive=false;
                    break;
                default:
                    tema="undefined";
                    break;
            }


        } while(isActive);

        logger.trace("Exiting application.");


    }
    private static void goToDisneyWorld(){
        boolean isActive = true;

        List<IDisneyland> parkovi = new ArrayList<IDisneyland>();
        parkovi.add(new MickeyTown());
        parkovi.add(new AdventureLand());
        parkovi.add(new FantasyLand());

        ((MickeyTown) parkovi.get(0)).addCharacter(new MickeyCharacter());
        ((MickeyTown) parkovi.get(0)).addCharacter(new MinnieCharacter());
        ((MickeyTown) parkovi.get(0)).addCharacter(new DummyCharacter());

        ((AdventureLand) parkovi.get(1)).addCharacter(new MickeyCharacter());
        ((AdventureLand) parkovi.get(1)).addCharacter(new MinnieCharacter());

        ((FantasyLand) parkovi.get(2)).addCharacter(new MinnieCharacter());
        ((FantasyLand) parkovi.get(2)).addCharacter(new DummyCharacter());

        do{

            System.out.println("Welcome to DisneyWorld, " +  name +", enter your selection: ");
            logger.trace("User " + name + " started the DisneyWorld.");


            for(int i = 0; i< parkovi.size(); i++){
                System.out.println(i+1 + " - Name: " + parkovi.get(i).getName() + ", description: " + parkovi.get(i).getDescription());
            }
            System.out.println(parkovi.size() + 1 + " - to exit"); //last option
            int option = scanner.nextInt();
            scanner.nextLine();
            option--;
            if(option >= 0 && option<parkovi.size()){
                visitPark(parkovi.get(option));
            } else if(option >= parkovi.size()){
                System.out.println("Thank You for playing!");
                isActive = false;
            }

        } while (isActive);
    }
    private static void visitPark(IDisneyland park){
        logger.trace("Posjeta parku " + park.getName());

        System.out.println();
        for(int i=0; i< park.getName().length() + 21;i++){
            System.out.print('*');
        }
        System.out.println();

        System.out.println("You are now at park: " + park.getName());

        List<DisneyCharacter> characters = park.getCharacters();

        for (DisneyCharacter character :
                characters) {
            System.out.printf("My name is: %s\nAnd my greeting is: \n%s\nAnd my picture is: %s\n\n", character.getName(), character.greetings(), character.picture());
        }


        System.out.println();
    }

    private static void goToMovieWorld(){
        System.out.println("Welcome to MovieWorld");
        File file = new File("C:\\Users\\B.I.O.S. S\\Desktop\\codecta-academy\\codecta-academy\\assignmentOne\\movies.txt");

        Map<String, ArrayList<Movie>> filmovi = new HashMap<>();
        System.out.println("Welcome to MovieWorld, " +  name +", enter your selection: ");

        logger.trace("User " + name + " started the MovieWorld.");


        try{
            Scanner skenerFilmova = new Scanner(file);

            while(skenerFilmova.hasNextLine()){
               String novaLinija = skenerFilmova.nextLine();
                String [] newMovie = novaLinija.split(",");

                if(newMovie.length==2) {
                    String zanr = newMovie[1].trim().toLowerCase();
                    String naziv = newMovie[0].trim();
                    Movie noviUnos = new Movie(naziv, zanr);

                    if(!filmovi.containsKey(zanr)){
                        filmovi.put(zanr, new ArrayList<Movie>());
                    }
                    filmovi.get(zanr).add(noviUnos);

                } else {
                    System.out.println("Greska u filmu!");
                    System.out.println(novaLinija);
                    logger.error("Movie error!");
                }
            }

            for (String zanr: filmovi.keySet()
                 ) {

                if(!zanroviZaIzbjeci.contains(zanr)){
                    System.out.println();
                    System.out.println("Genre: " + zanr.toUpperCase());

                    List<Movie> lista = filmovi.get(zanr);

                    for (Movie film: lista
                    ) {
                        System.out.println(film.getName());
                    }

                } else {
                    System.out.println(zanr + " je izbjegnut!");
                }
                System.out.println();

            }
            boolean isActive=true;

            do{
                System.out.println("Enter your options (enter genre or anything else to quit):");
                for (String zanr: filmovi.keySet()
                ) {
                    System.out.println(" Genre: " + zanr.toLowerCase());
                }

                String option = scanner.nextLine();
                option=option.toLowerCase();

                if(filmovi.containsKey(option.toLowerCase())){
                    List<Movie> filmoviPoZanru = filmovi.get(option);
                    System.out.println("Svi filmovi u zanru "+ option);
                    for (Movie film: filmoviPoZanru
                    ) {
                        System.out.println(film.getName());
                    }
                    System.out.println();
                }
                else{
                    isActive=false;
                    System.out.println("Thank You for visiting MovieWorld!");
                }

            } while(isActive);


        }catch(FileNotFoundException e)
        {
            System.out.println("File not found! " + e.getMessage());
             logger.error("File error" + e.getMessage());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("File error" + e.getMessage());
        }
    }
}
