package rs.ac.uns.ftn.sbnz.drools.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AmenityScore.class,
        Analytics.class,
        DistanceScore.class,
        Filtering.class,
        Finishing.class,
        HeatingScore.class,
        OptimalPrice.class,
        PetScore.class,
        Scaling.class,
        Reports.class
})
public class AllDroolsUnitTests {
}
