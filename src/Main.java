import models.Eleve;
import models.Professeur;
import models.Utilisateur;
import services.IEleveService;
import services.IUtilisateurService;
import services.impl.EleveServiceImpl;
import services.impl.UtilisateurService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Affichage de bienvenue
        System.out.println("******************************************************");
        System.out.println("               BIENVENU DANS L’APPLICATION ETAB v1.2");
        System.out.println("******************************************************");

        String idUtilisateur = null;
        String motDePasseUtilisateur = null;
        Utilisateur utilisateur = new Utilisateur(idUtilisateur, motDePasseUtilisateur);
        utilisateur.ajouterUtilisateurDefaut(); // Ajouter l'utilisateur par défaut au démarrage

        // Initialisation des variables
        Scanner scanner = new Scanner(System.in);
        boolean authenticated = false;
        LocalDateTime startTime = LocalDateTime.now();

        // Boucle d'authentification
        while (!authenticated) {
            System.out.println("CONNEXION");
            System.out.print("Entrez votre identifiant: ");
            String inputIdentifiant = scanner.nextLine();

            System.out.print("Entrez votre mot de passe: ");
            String inputMotDePasse = scanner.nextLine();

            IUtilisateurService utilisateurService = new UtilisateurService();
            authenticated = utilisateurService.authentification(inputIdentifiant, inputMotDePasse);

            if (authenticated) {
                System.out.println("******************************************************");
                System.out.println("               BIENVENU DANS L’APPLICATION ETAB v1.2");
                System.out.println("******************************************************");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println("Date et heure actuelles : " + startTime.format(formatter));
                System.out.println("******************************************************");

                boolean running = true;
                ArrayList<Eleve> listeEleves = new ArrayList<>();
                ArrayList<Professeur> listeProfesseurs = new ArrayList<>();

                while (running) {
                    // Affichage du menu principal
                    System.out.println("MENU:");
                    System.out.println("1: Gestion des élèves");
                    System.out.println("2: Gestion des professeurs");
                    System.out.println("3: Gestion des utilisateurs");
                    System.out.println("0: Quitter");
                    System.out.println("******************************************************");

                    System.out.print("Choisissez une option: ");
                    int choixMenu = scanner.nextInt();
                    scanner.nextLine(); // Pour consommer la ligne restante après nextInt()

                    switch (choixMenu) {
                        case 1:
                            boolean gestionEleves = true;
                            while (gestionEleves) {
                                // Menu de gestion des élèves
                                System.out.println("******************************************************");
                                System.out.println("GESTION DES ELEVES");
                                System.out.println("******************************************************");
                                System.out.println("Menu :");
                                System.out.println("1: Ajouter un élève");
                                System.out.println("2: Supprimer un élève");
                                System.out.println("3: Modifier les informations de l'élève");
                                System.out.println("4: Lister les élèves");
                                System.out.println("5: Obtenir le dernier élève ajouté");
                                System.out.println("6: Retour");
                                System.out.println("0: Accueil");
                                System.out.println("******************************************************");

                                System.out.print("Choisissez une option : ");
                                int choixEleve = scanner.nextInt();
                                scanner.nextLine(); // Consommer la ligne restante

                                switch (choixEleve) {
                                    case 1:
                                        // Ajouter un élève
                                        System.out.print("Entrez l'ID de l'élève : ");
                                        int id = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Entrez le nom de l'élève : ");
                                        String nom = scanner.nextLine();

                                        System.out.print("Entrez le prénom de l'élève : ");
                                        String prenom = scanner.nextLine();

                                        System.out.print("Entrez la ville de l'élève : ");
                                        String ville = scanner.nextLine();

                                        System.out.print("Entrez la classe de l'élève : ");
                                        String classe = scanner.nextLine();

                                        System.out.print("Entrez le matricule de l'élève : ");
                                        String matricule = scanner.nextLine();

                                        System.out.print("Entrez la date de naissance de l'élève (dd/MM/yyyy) : ");
                                        String dateNaissanceStr = scanner.nextLine();
                                        Date dateNaissance = null;
                                        try {
                                            dateNaissance = new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissanceStr);
                                        } catch (ParseException e) {
                                            System.out.println("Format de date invalide. Élève non ajouté.");
                                            break;
                                        }

                                        IEleveService eleveService = new EleveServiceImpl();

                                        Eleve eleve = new Eleve(0, dateNaissance, ville, nom, prenom, classe, matricule);
                                        eleveService.save(eleve);

                                        System.out.println("Élève ajouté avec succès !");
                                        break;
                                    case 2:
                                        // Supprimer un élève
                                        System.out.print("Entrez l'ID de l'élève à supprimer : ");
                                        int idASupprimer = scanner.nextInt();
                                        scanner.nextLine();

                                        Eleve eleveASupprimer = null;
                                        for (Eleve e : listeEleves) {
                                            if (e.getId() == idASupprimer) {
                                                eleveASupprimer = e;
                                                break;
                                            }
                                        }

                                        if (eleveASupprimer != null) {
                                            listeEleves.remove(eleveASupprimer);
                                            System.out.println("Élève supprimé avec succès !");
                                        } else {
                                            System.out.println("Élève introuvable.");
                                        }
                                        break;
                                    case 3:
                                        // Modifier les informations d'un élève
                                        System.out.print("Entrez l'ID de l'élève à modifier : ");
                                        int idAModifier = scanner.nextInt();
                                        scanner.nextLine();

                                        Eleve eleveAModifier = null;
                                        for (Eleve e : listeEleves) {
                                            if (e.getId() == idAModifier) {
                                                eleveAModifier = e;
                                                break;
                                            }
                                        }

                                        if (eleveAModifier != null) {
                                            System.out.print("Entrez le nouveau nom : ");
                                            eleveAModifier.setNom(scanner.nextLine());

                                            System.out.print("Entrez le nouveau prénom : ");
                                            eleveAModifier.setPrenom(scanner.nextLine());

                                            System.out.print("Entrez la nouvelle ville : ");
                                            eleveAModifier.setVille(scanner.nextLine());

                                            System.out.print("Entrez la nouvelle classe : ");
                                            eleveAModifier.setClasse(scanner.nextLine());

                                            System.out.print("Entrez le nouveau matricule : ");
                                            eleveAModifier.setMatricule(scanner.nextLine());

                                            System.out.println("Informations de l'élève mises à jour avec succès !");
                                        } else {
                                            System.out.println("Élève introuvable.");
                                        }
                                        break;
                                    case 4:
                                        // Lister les élèves
                                        System.out.println("Liste des élèves :");
                                        for (Eleve e : listeEleves) {
                                            System.out.println(e);
                                        }
                                        break;
                                    case 5:
                                        // Obtenir le dernier élève ajouté
                                        if (!listeEleves.isEmpty()) {
                                            System.out.println("Dernier élève ajouté : " + listeEleves.get(listeEleves.size() - 1));
                                        } else {
                                            System.out.println("Aucun élève n'a été ajouté.");
                                        }
                                        break;
                                    case 6:
                                        // Retour au menu principal
                                        gestionEleves = false;
                                        break;
                                    case 0:
                                        // Retour à l'accueil
                                        running = false;
                                        gestionEleves = false;
                                        break;
                                    default:
                                        System.out.println("Option invalide, veuillez réessayer.");
                                }
                            }
                            break;
                        case 2:
                            // Gestion des professeurs
                            boolean gestionProfesseur = true;
                            while (gestionProfesseur) {
                                // Menu de gestion des professeurs
                                System.out.println("******************************************************");
                                System.out.println("GESTION DES PROFESSEURS");
                                System.out.println("******************************************************");
                                System.out.println("Menu :");
                                System.out.println("1: Ajouter un professeur");
                                System.out.println("2: Supprimer un professeur");
                                System.out.println("3: Modifier les informations du professeur");
                                System.out.println("4: Lister les professeurs");
                                System.out.println("5: Obtenir le dernier professeur ajouté");
                                System.out.println("6: Retour");
                                System.out.println("0: Accueil");
                                System.out.println("******************************************************");

                                System.out.print("Choisissez une option : ");
                                int choixProfesseur = scanner.nextInt();
                                scanner.nextLine(); // Consommer la ligne restante

                                switch (choixProfesseur) {
                                    case 1:
                                        // Ajouter un professeur
                                        System.out.print("Entrez l'ID du professeur : ");
                                        int id = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Entrez le nom du professeur : ");
                                        String nom = scanner.nextLine();

                                        System.out.print("Entrez le prénom du professeur : ");
                                        String prenom = scanner.nextLine();

                                        System.out.print("Entrez la ville du professeur : ");
                                        String ville = scanner.nextLine();

                                        System.out.print("Entrez la matière enseignée par le professeur : ");
                                        String matiere = scanner.nextLine();

                                        //Professeur professeur = new Professeur(id, ville, nom, prenom, matiere);
                                        //listeProfesseurs.add(professeur);

                                        System.out.println("Professeur ajouté avec succès !");
                                        break;
                                    case 2:
                                        // Supprimer un professeur
                                        System.out.print("Entrez l'ID du professeur à supprimer : ");
                                        int idASupprimer = scanner.nextInt();
                                        scanner.nextLine();

                                        Professeur professeurASupprimer = null;
                                        for (Professeur p : listeProfesseurs) {
                                            if (p.getId() == idASupprimer) {
                                                professeurASupprimer = p;
                                                break;
                                            }
                                        }

                                        if (professeurASupprimer != null) {
                                            listeProfesseurs.remove(professeurASupprimer);
                                            System.out.println("Professeur supprimé avec succès !");
                                        } else {
                                            System.out.println("Professeur introuvable.");
                                        }
                                        break;
                                    case 3:
                                        // Modifier les informations d'un professeur
                                        System.out.print("Entrez l'ID du professeur à modifier : ");
                                        int idAModifier = scanner.nextInt();
                                        scanner.nextLine();

                                        Professeur professeurAModifier = null;
                                        for (Professeur p : listeProfesseurs) {
                                            if (p.getId() == idAModifier) {
                                                professeurAModifier = p;
                                                break;
                                            }
                                        }

                                        if (professeurAModifier != null) {
                                            System.out.print("Entrez le nouveau nom : ");
                                            professeurAModifier.setNom(scanner.nextLine());

                                            System.out.print("Entrez le nouveau prénom : ");
                                            professeurAModifier.setPrenom(scanner.nextLine());

                                            System.out.print("Entrez la nouvelle ville : ");
                                            professeurAModifier.setVille(scanner.nextLine());

                                            System.out.print("Entrez la nouvelle matière enseignée : ");
                                            //professeurAModifier.setMatiere(scanner.nextLine());

                                            System.out.println("Informations du professeur mises à jour avec succès !");
                                        } else {
                                            System.out.println("Professeur introuvable.");
                                        }
                                        break;
                                    case 4:
                                        // Lister les professeurs
                                        System.out.println("Liste des professeurs :");
                                        for (Professeur p : listeProfesseurs) {
                                            System.out.println(p);
                                        }
                                        break;
                                    case 5:
                                        // Obtenir le dernier professeur ajouté
                                        if (!listeProfesseurs.isEmpty()) {
                                            System.out.println("Dernier professeur ajouté : " + listeProfesseurs.get(listeProfesseurs.size() - 1));
                                        } else {
                                            System.out.println("Aucun professeur n'a été ajouté.");
                                        }
                                        break;
                                    case 6:
                                        // Retour au menu principal
                                        gestionProfesseur = false;
                                        break;
                                    case 0:
                                        // Retour à l'accueil
                                        running = false;
                                        gestionProfesseur = false;
                                        break;
                                    default:
                                        System.out.println("Option invalide, veuillez réessayer.");
                                }
                            }
                            break;
                        case 3:
                            // Gestion des utilisateurs
                            boolean gestionUtilisateur = true;
                            while (gestionUtilisateur) {
                                System.out.println("******************************************************");
                                System.out.println("GESTION DES UTILISATEURS");
                                System.out.println("******************************************************");
                                System.out.println("Menu :");
                                System.out.println("1: Ajouter un utilisateur");
                                System.out.println("2: Supprimer un utilisateur");
                                System.out.println("3: Modifier les informations d'un utilisateur");
                                System.out.println("4: Lister les utilisateurs");
                                System.out.println("5: Retour");
                                System.out.println("0: Accueil");
                                System.out.println("******************************************************");

                                System.out.print("Choisissez une option : ");
                                int choixUtilisateur = scanner.nextInt();
                                scanner.nextLine(); // Consommer la ligne restante

                                switch (choixUtilisateur) {
                                    case 1:
                                        // Ajouter un utilisateur
                                        System.out.print("Entrez l'identifiant de l'utilisateur : ");
                                        idUtilisateur = scanner.nextLine();

                                        System.out.print("Entrez le mot de passe de l'utilisateur : ");
                                        motDePasseUtilisateur = scanner.nextLine();

                                        Utilisateur nouvelUtilisateur = new Utilisateur(idUtilisateur, motDePasseUtilisateur);
                                        utilisateurService.ajouterCompte(idUtilisateur,motDePasseUtilisateur);

                                        System.out.println("Utilisateur ajouté avec succès !");
                                        break;
                                    case 2:
                                        // Supprimer un utilisateur
                                        System.out.print("Entrez l'identifiant de l'utilisateur à supprimer : ");
                                        String idUtilisateurASupprimer = scanner.nextLine();

                                        utilisateurService.supprimerCompte(idUtilisateurASupprimer);

                                        System.out.println("Utilisateur supprimé avec succès !");
                                        break;
                                    case 3:
                                        // Modifier les informations d'un utilisateur
                                        System.out.print("Entrez l'identifiant de l'utilisateur à modifier : ");
                                        String idUtilisateurAModifier = scanner.nextLine();

                                        System.out.print("Entrez le nouveau mot de passe : ");
                                        String nouveauMotDePasse = scanner.nextLine();

                                        utilisateurService.modifierMotDepass(idUtilisateurAModifier, nouveauMotDePasse);

                                        System.out.println("Informations de l'utilisateur mises à jour avec succès !");
                                        break;
                                    case 4:
                                        // Lister les utilisateurs
                                        ArrayList<Utilisateur> listeUtilisateurs = (ArrayList<Utilisateur>) utilisateurService.listeUtilisateur();
                                        System.out.println("Liste des utilisateurs :");
                                        for (Utilisateur u : listeUtilisateurs) {
                                            System.out.println(u);
                                        }
                                        break;
                                    case 5:
                                        // Retour au menu principal
                                        gestionUtilisateur = false;
                                        break;
                                    case 0:
                                        // Retour à l'accueil
                                        running = false;
                                        gestionUtilisateur = false;
                                        break;
                                    default:
                                        System.out.println("Option invalide, veuillez réessayer.");
                                }
                            }
                            break;
                        case 0:
                            // Quitter
                            running = false;
                            System.out.println("******************************************************");
                            System.out.println("MERCI D'AVOIR UTILISÉ L’APPLICATION ETAB v1.2");
                            System.out.println("******************************************************");
                            break;
                        default:
                            System.out.println("Option invalide, veuillez réessayer.");
                    }
                }
            } else {
                System.out.println("Identifiant ou mot de passe incorrect, veuillez réessayer.");
            }
        }
    }
}
