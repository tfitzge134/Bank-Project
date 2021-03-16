package com.bank.main;

import com.bank.dao.PersonDAO;
import com.bank.dao.impl.PersonDAOImpl;
import com.bank.model.Person;

public class PersonMain {

	public static void main(String[] args) {
		//System.out.println("Welcome to Dr.Vinay's Player App V1.0");
		System.out.println("================================================");
		int id = 51;
		
		PersonDAO dao = new PersonDAOImpl();
		Person p = new Person(id,"Maria", "Smith", "ms.@bank.com", "4132326244",
				"Teacher", "1976-06-09", 
				"PAssord", "FALSE");
		int c = dao.addPerson(p);
		if(c>0) {
			System.out.println("person added");
			System.out.println(p);
		
		}else {
			System.out.println("adding person failed");
		}
		

		

	}

}

//System.out.println("Welcome to Dr.Vinay's Player App V1.0");
//System.out.println("================================================");
//int ch = 0;
//Scanner scanner = new Scanner(System.in);
//TeamService teamService = new TeamServiceImpl();
//PlayerCRUDService playercrudService = new PlayerCRUDServiceImpl();
//do {
//	System.out.println("Player MENU");
//	System.out.println("-----------------");
//	System.out.println("1)Add Player");
//	System.out.println("2)Update Player");
//	System.out.println("3)Delete Player");
//	System.out.println("4)List All Players");
//	System.out.println("5)Search Player by any of these id,name,age,city,gender");
//	System.out.println("6)List all Teams");
//	System.out.println("7)Add Team");
//	System.out.println("8)EXIT");
//	System.out.println("Please enter an appropriate Search Option(1-8)");
//	try {
//		ch = Integer.parseInt(scanner.nextLine());
//	} catch (NumberFormatException e) {
//
//	}
//	switch (ch) {
//	case 1:
//		System.out.println("Enter below Player Details to Create the Player");
//		Player player = new Player();
//		try {
//			System.out.println("Enter player id:");
//			player.setId(Integer.parseInt(scanner.nextLine()));
//
//		} catch (NumberFormatException e) {
//			System.out.println("Player id should be number only");
//			break;
//		}
//		try {
//			System.out.println("Enter player age:");
//			player.setAge(Integer.parseInt(scanner.nextLine()));
//		} catch (NumberFormatException e) {
//			System.out.println("Player age should be number only");
//			break;
//		}
//		System.out.println("Enter Player Name");
//		player.setName(scanner.nextLine());
//		System.out.println("Enter Player City");
//		player.setCity(scanner.nextLine());
//		System.out.println("Enter Player Gender(m/f/F/M)");
//		player.setGender(scanner.nextLine());
//		System.out.println("Please select the teamNumber from the below list");
//		try {
//			List<Team> teamList = teamService.getAllTeams();
//			for (int i = 0; i < teamList.size(); i++) {
//				System.out.println((i + 1) + ")" + teamList.get(i).getTeamName());
//			}
//			System.out.println(teamList.size() + 1 + ")Not preffered for now");
//
//			try {
//				System.out.println("Please enter your team choice between 1 - " + (teamList.size() + 1));
//				int choice = Integer.parseInt(scanner.nextLine());
//
//				if (choice > 0 && choice <= teamList.size() + 1) {
//					if (choice == teamList.size() + 1) {
//						player.setTeam(new Team());
//					} else {
//						player.setTeam(teamList.get(choice - 1));
//					}
//				} else {
//					System.out.println("Invalid choice");
//				}
//
//			} catch (NumberFormatException e) {
//				System.out.println("Player team choice should be number only");
//				break;
//			}
//		} catch (BusinessException e) {
//			System.out.println(e.getMessage());
//			break;
//		}
//		try {
//			if (playercrudService.createPlayer(player) == 1) {
//				System.out.println("Player Registered Successfully with below details");
//				System.out.println(player);
//			}
//		} catch (BusinessException e) {
//			System.out.println(e.getMessage());
//		}
//
//		break;
//	case 2:
//		System.out.println("Under construction");
//
//		break;
//	case 3:
//		System.out.println("Under construction");
//
//		break;
//	case 4:
//		System.out.println("Under construction");
//
//		break;
//	case 5:
//		int option = 0;
//		PlayerSearchService playerSearchService = new PlayerSearchServiceImpl();
//		do {
//			System.out.println("Select below search option to search a player ");
//			System.out.println("1)By Id");
//			System.out.println("2)By Name");
//			System.out.println("3)By Age");
//			System.out.println("4)By City");
//			System.out.println("5)By TeamName");
//			System.out.println("6)Go back to main menu");
//			System.out.println("Enter choice between 1-6");
//			try {
//				option = Integer.parseInt(scanner.nextLine());
//			} catch (NumberFormatException e) {
//			}
//
//			switch (option) {
//			case 1:
//				System.out.println("Enter Player Id to get the Player details");
//				try {
//					int id = Integer.parseInt(scanner.nextLine());
//					// code here for PlayerSearchService
//					Player p = playerSearchService.getPlayerById(id);
//					if (p != null) {
//						System.out.println("Player found with id : " + id + " below are the details");
//						System.out.println(p);
//					}
//				} catch (NumberFormatException e) {
//					System.out.println("Player ID should be number only");
//				} catch (BusinessException e) {
//					System.out.println(e.getMessage());
//				}
//
//				break;
//			case 2:
//				System.out.println("Under construction");
//
//				break;
//			case 3:
//				System.out.println("Under construction");
//
//				break;
//
//			case 4:
//				System.out.println("Under construction");
//
//				break;
//			case 5:
//				System.out.println("Enter TeamName to search for players for that team");
//				String teamname=scanner.nextLine();
//				try {
//					List<Player> playerTeamList = playerSearchService.getPlayersByTeamName(teamname);
//					System.out.println("Printing players for the team "+teamname);
//					for(Player p:playerTeamList) {
//						System.out.println(p);
//					}
//				} catch (BusinessException e) {
//					System.out.println(e.getMessage());
//				}
//				break;
//			case 6:
//				System.out.println("Going back to main menu........");
//
//				break;
//			default:
//				System.out.println("Invalid search option it should be number between 1-6 only ");
//				break;
//			}
//		} while (option != 6);
//		break;
//	case 6:
//		List<Team> teamList;
//		try {
//			teamList = teamService.getAllTeams();
//			System.out.println("Printing all the teams");
//			for (int i = 0; i < teamList.size(); i++) {
//				System.out.println((i + 1) + ")" + teamList.get(i));
//			}
//		} catch (BusinessException e) {
//			System.out.println(e.getMessage());
//		}
//
//		break;
//	case 7:
//		System.out.println("Under construction");
//
//		break;
//	case 8:
//		System.out.println("Thankq for using the Player APP V1.0.....");
//
//		break;
//
//	default:
//		System.out.println("Invalid Choice... Please enter a proper choice between 1-8 only.......");
//		break;
//	}
//} while (ch != 8);
//
//}









