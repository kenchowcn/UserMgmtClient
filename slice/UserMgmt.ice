#ifndef USER_MGMT_ICE
#define USER_MGMT_ICE

module UserMgmt
{
    struct UserInfo
    {
        string userName;
        string UserPassord;
    };

    enum ValidationResult
    {
        Welcome,
        UsernameNonexistant,
        PasswordIncorrect,
        BalanceInsufficient,
        UnknowResult
    };

    /*
    * what is an sessionToken?
    * 1. Easy to update.
    * 2. More secure than including uer information to make a request every time.
    */
    struct LoginResult
    {
        ValidationResult validationResult;
        long sessionToken;
    };

    interface UserManagement
    {
        void Login(UserInfo user, out LoginResult result);
    };
};

#endif
