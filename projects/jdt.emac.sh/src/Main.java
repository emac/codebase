import java.util.StringTokenizer;



/*
 * Created on Oct 20, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

/**
 * @author bishen
 */
public class Main
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String text = "a$$b$$Build.pl -deliverable SOATestsExtended -DnoServicePrebuild=true -buildLibrary PolicyEnforcementService,PolicyEnforcementServiceConsumer,PolicyService,RateLimiterService,SecurityFramework,SecurityFrameworkExtended,ObjectIdMapperService,SOACommon,SOAClient,AuthorizationService -useProjects SecurityItemManageConsumer,CalculatorTestServiceConsumer,SecurityItemManageImpl,SOAServerExtended,SOATestsExtended,SOATests,AuthenticationServiceConsumer,AuthenticationServiceImpl,AuthorizationServiceConsumer,AuthorizationServiceImpl,BlacklistServiceConsumer,BlacklistServiceImpl,CalculatorTestServiceImpl,ChallengeServiceConsumer,ChallengeServiceImpl,DALSecurityTests,GlobalEnvironment,GroupMembershipServiceConsumer,GroupMembershipServiceImpl,IAFServiceConsumer,MarketPlaceServiceCommon,PolicyEnforcementServiceImpl,PolicyServiceConsumer,PolicyServiceImpl,RateLimiterServiceConsumer,RateLimiterServiceImpl,SecurityAdminServiceConsumer,SecurityAdminServiceImpl,SOAClientExtended,WhitelistServiceConsumer,WhitelistServiceImpl,AdminTestItemValidationConsumer,AdminTestItemValidationImpl,SOAQAValidations,StringStatServiceImpl,ObjectIdMapperServiceImpl,ObjectIdMapperServiceConsumer -train e675 -pooltype feature -locale core -poolname ${host.name}.dev.ebay.com -dbenv dev -clean -eclipse -container ce -CalLogServer calpub.arch.ebay.com  -runbuild2 -includeTests -antarg -Dglobal.run.tests=true";
        StringTokenizer st = new StringTokenizer(text, "$$");
        while (st.hasMoreTokens())
        {
            System.out.println(st.nextToken());
        }
        for (String s : text.split("\\$\\$"))
        {
            System.out.println(s);
        }
        
        /*
        String aa = "aabb";
        String a = "a";
        System.out.println(aa.lastIndexOf(a));
        System.out.println(aa.substring(aa.lastIndexOf(a)));
        System.out.println(a.lastIndexOf(a));
        
        /*
        Pattern p = Pattern.compile("/");
        "c:/views".replaceAll("/", File.separator);
        /*
        OUTER: for (int i = 0; i < 10; i++)
        {
            System.out.println("Outer: " + i);
            INNER: for (int j = 0; j < 10; j++)
            {
                if ( j % 2 == 0 )
                {
                    System.out.println("Inner: " + j);
                    continue INNER;
                }
                else
                {
                    continue OUTER;
                }
            }
        }
        /*
        MyEnum enm = null;
        switch (enm)
        {
            case A:
            {
                System.out.println(MyEnum.A);
                break;
            }
            case B:
            {
                System.out.println(MyEnum.B);
                break;
            }
            default:
        }
        */
    }

    @SuppressWarnings("unused")
	private enum MyEnum
    {
        A, B;
    }

}
