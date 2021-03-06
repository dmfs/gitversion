package org.dmfs.gradle.gitversion.git.changetypefacories.condition;

import org.dmfs.gradle.gitversion.git.changetypefacories.Condition;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.single.Backed;
import org.eclipse.jgit.revwalk.RevCommit;


public final class AllOf implements Condition
{
    private final Iterable<? extends Condition> mDelegates;


    public AllOf(Iterable<? extends Condition> delegates)
    {
        mDelegates = delegates;
    }


    @Override
    public boolean matches(RevCommit commit, String branch)
    {
        return new Backed<>(new First<>(m -> !m, new Mapped<>(c -> c.matches(commit, branch), mDelegates)), true).value();
    }
}
