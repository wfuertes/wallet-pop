package com.wfuertes;

import com.wfuertes.sql.tables.records.EarningsRecord;
import org.junit.Assert;
import org.junit.Test;


public class WalletAppTest {

    @Test
    public void shouldAnswerWithTrue() {
        Assert.assertFalse(new EarningsRecord().equals(null));
    }
}
