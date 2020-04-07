package org.xokyopo.massiveandsortingtests.treegenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xokyopo.massiveandsorting.treegenerator.CompanyTreeBuilder;
import org.xokyopo.massiveandsorting.treegenerator.interfaces.XmlTreeExportImpl;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CompanyTreeBuilderTest {
    private XmlTreeExportImpl treeExport;
    private ValueOfTest valueOfTest;

    static class ValueOfTest{
        public String[] inArr;
        public String output;

        public ValueOfTest(String output, String... inArr) {
            this.inArr = inArr;
            this.output = output;
        }
    }

    @Parameterized.Parameters
    public static Collection<Object> creatParamList() {
        return Arrays.asList(new Object[]{
                new ValueOfTest("<0><1/><2/></0>", "1 0", "2 0"),
                new ValueOfTest("<0><1><3/><4/></1><2/></0>", "1 0", "3 1", "4 1", "2 0"),
                new ValueOfTest("<0><1/><2><5/><6/></2></0>", "1 0", "2 0", "5 2", "6 2"),
                new ValueOfTest("<0><1/><2/><3/><4/><5/></0>", "1 0", "2 0", "3 0", "4 0", "5 0"),
                new ValueOfTest("<0><1><3><4/></3></1><2/></0>", "1 0", "2 0", "3 1", "4 3"),
                new ValueOfTest("<0><1><3><5/></3></1><2><4><5/></4></2></0>", "1 0", "2 0", "3 1", "5 3", "4 2", "5 4"),
        });
    }

    @Before
    public void init() {
        //TODO создание нового класса перед запуском теста
        this.treeExport = new CompanyTreeBuilder();
    }

    public CompanyTreeBuilderTest(ValueOfTest valueOfTest) {
        this.valueOfTest = valueOfTest;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(
                this.treeExport.getXMLTree(this.valueOfTest.inArr).toCharArray(),
                this.valueOfTest.output.toCharArray()
        );
    }
}
