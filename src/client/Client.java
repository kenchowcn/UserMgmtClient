package client;

import UserMgmt.LoginResultHolder;
import UserMgmt.UserInfo;
import UserMgmt.UserManagementPrx;
import UserMgmt.UserManagementPrxHelper;

public class Client extends Ice.Application{

	class ShutdownHook extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                communicator().destroy();
            }
            catch(Ice.LocalException ex)
            {
                ex.printStackTrace();
            }
        }
    }
	
	@Override
	public int run(String[] args) {
		// TODO Auto-generated method stub
		
		if(args.length > 0)
        {
            System.err.println(appName() + ": too many arguments");
            return 1;
        }
		
		setInterruptHook(new ShutdownHook());
		
		UserManagementPrx twoway = UserManagementPrxHelper.checkedCast(communicator().propertyToProxy("UserManagement.Proxy").ice_twoway().ice_secure(false));
        if(twoway == null)
        {
            System.err.println("invalid proxy");
            return 1;
        }
        
        UserInfo user = new UserInfo("Jenny", "123456");
        LoginResultHolder result = new LoginResultHolder();
        
        twoway.Login(user, result);
        
        System.out.println("Result:{" +result.value.sessionToken + ", " + result.value.validationResult+ "}");
		
		return 0;
	}
	
	public static void main(String[] args)
    {
        Client app = new Client();
        int status = app.main("Client", args, "conf/config.client");
        System.exit(status);
    }

}

