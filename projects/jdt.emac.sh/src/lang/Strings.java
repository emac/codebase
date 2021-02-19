package lang;

import java.io.File;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * @author Emac
 * @date 2021/2/19
 */
public class Strings {

    public static void main(String[] args) {
        String aa = "aabb";
        String a = "a";
        System.out.println(aa.lastIndexOf(a));
        System.out.println(aa.substring(aa.lastIndexOf(a)));
        System.out.println(a.lastIndexOf(a));

        // StringTokenizer

        String text = "a$$b$$Build.pl -deliverable SOATestsExtended -DnoServicePrebuild=true -buildLibrary PolicyEnforcementService,PolicyEnforcementServiceConsumer,PolicyService,RateLimiterService,SecurityFramework,SecurityFrameworkExtended,ObjectIdMapperService,SOACommon,SOAClient,AuthorizationService -useProjects SecurityItemManageConsumer,CalculatorTestServiceConsumer,SecurityItemManageImpl,SOAServerExtended,SOATestsExtended,SOATests,AuthenticationServiceConsumer,AuthenticationServiceImpl,AuthorizationServiceConsumer,AuthorizationServiceImpl,BlacklistServiceConsumer,BlacklistServiceImpl,CalculatorTestServiceImpl,ChallengeServiceConsumer,ChallengeServiceImpl,DALSecurityTests,GlobalEnvironment,GroupMembershipServiceConsumer,GroupMembershipServiceImpl,IAFServiceConsumer,MarketPlaceServiceCommon,PolicyEnforcementServiceImpl,PolicyServiceConsumer,PolicyServiceImpl,RateLimiterServiceConsumer,RateLimiterServiceImpl,SecurityAdminServiceConsumer,SecurityAdminServiceImpl,SOAClientExtended,WhitelistServiceConsumer,WhitelistServiceImpl,AdminTestItemValidationConsumer,AdminTestItemValidationImpl,SOAQAValidations,StringStatServiceImpl,ObjectIdMapperServiceImpl,ObjectIdMapperServiceConsumer -train e675 -pooltype feature -locale core -poolname ${host.name}.dev.ebay.com -dbenv dev -clean -eclipse -container ce -CalLogServer calpub.arch.ebay.com  -runbuild2 -includeTests -antarg -Dglobal.run.tests=true";
        StringTokenizer st = new StringTokenizer(text, "$$");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
        for (String s : text.split("\\$\\$")) {
            System.out.println(s);
        }

        // Pattern

        Pattern p = Pattern.compile("/");
        "c:/views".replaceAll("/", File.separator);
    }
}
