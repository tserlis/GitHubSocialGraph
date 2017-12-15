import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.jfree.ui.RefineryUtilities;
 

/**
 * 
 * @author Thomas Erlis
 * @version 1.0
 *
 */
public class GithubAccess {

	public static void main(String[] args) throws IOException {
		System.out.println("Enter your username:\n");
		Scanner input = new Scanner (System.in);
		String username = input.next();
		System.out.println("Enter your password:\n");
		String password = input.next();
		input.close();
		User user = FindUser(username, password);
		System.out.println("Information on this user:\nLogin: "
							+ user.getLogin() + "\nName: "
							+ user.getName() + "\nEmail: "
							+ user.getEmail() + "\nFollowers: "
							+ user.getFollowers() + "\nFollowing: "
							+ user.getFollowing() + "\nPublic Repos: "
							+ user.getPublicRepos() + "\nPrivate Repos: "
							+ user.getTotalPrivateRepos());
		BarChart chart = new BarChart ("Public vs Private Repos", 
		         "Public vs Private Repos");
		chart.addData(user.getPublicRepos(), "Public Repos", "");
		chart.addData(user.getTotalPrivateRepos(), "Private Repos", "");
		      chart.pack( );        
		      RefineryUtilities.centerFrameOnScreen( chart );        
		      chart.setVisible( true ); 
		RepositoryService repoService = new RepositoryService();
		PieChart pie = new PieChart("Repo Contributor Numbers");
		for (Repository repo : repoService.getRepositories(username)) {
			ArrayList repoContribs = (ArrayList) repoService.getContributors(repo, true);
				System.out.println(repo.getName() + " Contributors " + repoContribs.size());
				pie.addData(repo.getName(), repoContribs.size());
				
		}
		pie.setSize( 560 , 367 );    
	      RefineryUtilities.centerFrameOnScreen( pie );    
	      pie.setVisible( true ); 
		}
	
	private static User FindUser(String username, String password) throws IOException {
		GitHubClient client = new GitHubClient();
		client.setCredentials(username, password);
		UserService userService = new UserService(client);
		return userService.getUser();
	}
	
	

}
