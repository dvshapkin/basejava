package model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SectionTest.class,
        MultiSectionTest.class,
        ResumeTest.class
})
public class AllTests {
}