import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *	Population - a utility that is able to sort a population data base and give queries
	that are useful to user from it
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Saketh Korada 
 *	@since	1/12/22
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	private double time;
	private double startMillisec;
	private double endMillisec;
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	public Population()
	{	cities = new ArrayList<>();
		time = 0;
		Scanner read = FileUtils.openToRead(DATA_FILE).useDelimiter("[\t\n]");
		while(read.hasNextLine())
		{
			
			String state = read.next();
			

			String name = read.next();
			
			String type = read.next();
			
			
			String pop = read.next();
			
			int population = Integer.parseInt(pop);
			
			
			cities.add(new City(name, state,type, population));
			read.nextLine();
		}
		


	}
	//main method
	public static void main(String[]args)
	{
		Population sk = new Population();
		sk.run();
	}
	
	/**
	 * prints the first 50 (if not 50, howmuch ever there is) 
	 * in the right format 
	 * @param List<City> the list to be printed
	 * 
	 * 
	 */
	public void printFirstFifty(List<City> list)
	{
		System.out.printf("    %-22s %-22s %-12s %12s\n","State", "City", "Type",
		"Population");
		if(list.size()>= 50)
		{
		for(int i = 0; i < 50;i++)
		{
			System.out.printf("%2s: %s\n",i+1, list.get(i));
		}
	}
	else
	{
		for(int i = 0; i < list.size();i++)
		{
			System.out.printf("%2s: %s\n",i+1, list.get(i));
		}
	}
		System.out.println("\n\n");
	}
	//runs all important methods
	/**
	 * uses the prompt class and a while loop to ask the prompt 
	 * based on prompt, code is run that calls on right methods
	 */
	public void run()
	{
		printIntroduction();
		printMenu();
		


		
		int input = Prompt.getInt("\n\nEnter Selection", 1, 9);
		while(input!= 9)
		{
			if(input == 7 || input == 8)
			{
				

			}
			else
			{
				if(input == 1)
				{
					startMillisec = System.currentTimeMillis();
					ascendingPop();
					endMillisec = System.currentTimeMillis();
					time = endMillisec-startMillisec;
					
					System.out.println("\nFifty least populous cities");
					printFirstFifty(cities);

					System.out.println("\n\nElapsed Time "+time+ " milliseconds");
				}
				if(input == 2)
				{
					startMillisec = System.currentTimeMillis();
					descendingPop(cities);
					endMillisec = System.currentTimeMillis();
					time = endMillisec-startMillisec;
					System.out.println("\nFifty most populous cities");
					printFirstFifty(cities);

					System.out.println("\n\nElapsed Time "+time+ " milliseconds");

				}
				if(input == 3)
				{
					startMillisec = System.currentTimeMillis();
					ascendingName();
					endMillisec = System.currentTimeMillis();
					time = endMillisec-startMillisec;
					System.out.println("\nFifty cities sorted by name");
					printFirstFifty(cities);

					System.out.println("\n\nElapsed Time "+time+ " milliseconds");

				}
				if(input == 4)
				{
					startMillisec = System.currentTimeMillis();
					descendingName(cities);
					endMillisec = System.currentTimeMillis();
					time = endMillisec-startMillisec;
					System.out.println("\nFifty cities sorted by name descending");
					printFirstFifty(cities);

					System.out.println("\n\nElapsed Time "+time+ " milliseconds");

				}
				if(input == 5)
				{
					String state = Prompt.getString("Enter State name (ie. Alabama) ");
					while(notValidState(state))
					{
						System.out.println("\nERROR: "+ state+" is not valid");
						state = Prompt.getString("Enter State name (ie. Alabama) ");
					}

					List<City> list = sameState(state, cities);
					startMillisec = System.currentTimeMillis();
					descendingPop(list);
					endMillisec = System.currentTimeMillis();
					time = endMillisec-startMillisec;
					System.out.println("\nFifty most populous cities in "+state);
					printFirstFifty(list);

					System.out.println("\n\nElapsed Time "+time+ " milliseconds");



				}
				if(input == 6)
				{

					String name = Prompt.getString("Enter city name");
					while(notValidCity(name))
					{
						System.out.println("\nERROR: "+ name+" is not valid");
						name = Prompt.getString("Enter city name");

					}

					List<City> list = sameName(name, cities);
					startMillisec = System.currentTimeMillis();
					descendingPop(list);
					endMillisec = System.currentTimeMillis();
					time = endMillisec-startMillisec;
					System.out.println("\nCity "+ name+ " by population" );
					printFirstFifty(list);

					System.out.println("\n\nElapsed Time "+time+ " milliseconds");

				}

			}
			printMenu();
			
			input = Prompt.getInt("\n\nEnter Selection", 1, 9);

		}
		System.out.println("\n\nThanks for using population.");


	}
	
	/**
	 * a method to check if the input is a valid state
	 * @param String name the name of state to be checked
	 * @return true if it is not valid;false otherwise
	 */

	public boolean notValidState(String state)
	{
		for(City city: cities)
		{
			if(city.getState().equals(state))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * a method to check if the input is a valid City
	 * @param String name the name of city to be checked
	 * @return true if it is not valid;false otherwise
	 */
	public boolean notValidCity(String name)
	{
		for(City city: cities)
		{
			if(city.getName().equals(name))
			{
				return false;
			}
		}
		return true;

	}

	
	/**
	 * Sorts the list in ascending order using selection sort
	 * Simple linear sort that swaps 1 by 1
	 * 
	 */
	public void ascendingPop()
	{
		
		for(int i = 0; i < cities.size();i++)
		{
			City min= cities.get(i);
			int minIndex = i;
			for(int j = i+1; j < cities.size();j++)
			{
				if(cities.get(j).compareTo(min) < 0 )
				{
					minIndex = j;
					min = cities.get(j);

				}


			}

			City temp = cities.get(i);
			cities.set(i,min);
			cities.set(minIndex, temp);
		}

		

	}
	/**
	 * Sorts teh list in descening order using merge sort
	 *	Uses recursion to divide and conquer for a quick sort
	 * @param List<City> the list that will be sorted 
	 */
	public void descendingPop(List<City> list)
	{

		int length = list.size();
		int leftLength = length/2;

		List<City> left = new ArrayList<>();
		List<City> right = new ArrayList<>();

		if(length < 2) return;
		for(int i = 0; i < leftLength;i++)
		{
			left.add(list.get(i));
		}
		for(int i = leftLength; i < length;i++)
		{
			right.add(list.get(i));
		} 

		descendingPop(left);
		descendingPop(right);
	
		int i, j,k;
		i = j = k = 0;
		int leftSize = left.size();
		int rightSize = right.size();

		while( i < leftSize && j < rightSize)
		{
			if(left.get(i).compareTo(right.get(j))> 0 )
			{
				list.set(k, left.get(i));
				i++;

			}
			else
			{
				list.set(k, right.get(j));
				j++;
			}
			k++;
		}

		while( i< leftSize)
		{
			list.set(k, left.get(i));
			i++;k++;
		}
		while( j < rightSize)
		{
			list.set(k, right.get(j));
			j++;k++;
		}

	}

	/**
	 * Sorts the list in ascneding name order using insertion sort.
	 * Create an empty list at the start
	 * elements are added based on position
	 */
	public void ascendingName()
	{
		List<City> list = new ArrayList<>();
		
		
		
		for(int i = 0; i < cities.size();i++)
		{
			int j = 0;
			while(j < list.size())
			{
				if(cities.get(i).getName().compareTo(list.get(j).getName()) < 0 )
				{
					break;
				}
				if(cities.get(i).getName().compareTo(list.get(j).getName()) == 0 )
				{
					if(cities.get(i).getPop() < list.get(j).getPop())break;
				}
				j++;
			}
			list.add(j, cities.get(i));

		}
		cities = list;
	}

	/**
	 * Sorts the list in descending name order using merge sort
	 * It contains both the divide (into individual arrays) and conquering
	 * (which merges) everything back
	 * 
	 * @param List<City> list that will be sorted in the future
	 */
	public void descendingName(List<City> list)
	{
		int length = list.size();
		int leftLength = length/2;

		List<City> left = new ArrayList<>();
		List<City> right = new ArrayList<>();

		if(length < 2) return;
		for(int i = 0; i < leftLength;i++)
		{
			left.add(list.get(i));
		}
		for(int i = leftLength; i < length;i++)
		{
			right.add(list.get(i));
		} 

		descendingName(left);
		descendingName(right);
	
		int i, j,k;
		i = j = k = 0;
		int leftSize = left.size();
		int rightSize = right.size();

		while( i < leftSize && j < rightSize)
		{
			// if(left.get(i).getName().compareTo(right.get(j).getName())  == 0 )
			// {
			// 	if(left.get(i).getPop() <= right.get(i).getPop())
			// 	{
			// 		list.set(k, left.get(i));
			// 		i++;

			// 	}
			// 	else
			// 	{
			// 		list.set(k, right.get(j));
			// 		j++;

			// 	}
				
			// }
			if(left.get(i).getName().compareTo(right.get(j).getName()) > 0)
			{
				list.set(k, left.get(i));
				i++;

			}
			else
			{
				list.set(k, right.get(j));
				j++;
			}
			k++;
		}

		while( i< leftSize)
		{
			list.set(k, left.get(i));
			i++;k++;
		}
		while( j < rightSize)
		{
			list.set(k, right.get(j));
			j++;k++;
		}



		

	}
	
	
	/**
	 * returns a list with elements that match the city name 
	 * @param String name the name that is to be compared 
	 * @param List<> list the list that will be sorted 
	 * @return List<> the list with only the right name
	 * 
	 */

	public List<City> sameName(String name, List<City> list)
	{
		List<City> result = new ArrayList<>();
		for(int i = 0; i < list.size();i++)
		{
			if(list.get(i).getName().equals(name))
			{
				result.add(list.get(i));
				
			}
		}
		
		return result;

	}
	
	
	/**
	 * returns a list with elements that match the state 
	 * @param String state the state that is to be compared 
	 * @param List<> list the list that will be sorted 
	 * @return List<> the list with only the right state
	 * 
	 */
	public List<City> sameState(String state, List<City> list)
	{
		List<City> result = new ArrayList<>();
		for(int i = 0; i < list.size();i++)
		{
			if(list.get(i).getState().equals(state))
			{
				result.add(list.get(i));
				
			}
		}
		
		return result;


	}


	
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}
