package com.vinicius.twitter.converter;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;

public interface Converter<TO, FROM>
{
    FROM from(TO to);

    TO to(FROM from);

    default List<FROM> fromList(List<TO> tos)
    {
        List<FROM> froms = null;

        if (isNotEmpty(tos))
        {
            froms = new ArrayList<>();
            for (TO to : tos)
            {
                froms.add(from(to));
            }
        }

        return froms;
    }

    default List<TO> toList(List<FROM> froms)
    {
        List<TO> tos = null;

        if (isNotEmpty(froms))
        {
            tos = new ArrayList<>();
            for (FROM from : froms)
            {
                tos.add(to(from));
            }
        }

        return tos;
    }
}
