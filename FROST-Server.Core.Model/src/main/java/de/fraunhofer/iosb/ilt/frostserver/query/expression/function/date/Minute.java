/*
 * Copyright (C) 2016 Fraunhofer Institut IOSB, Fraunhoferstr. 1, D 76131
 * Karlsruhe, Germany.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.fraunhofer.iosb.ilt.frostserver.query.expression.function.date;

import de.fraunhofer.iosb.ilt.frostserver.query.expression.Expression;
import de.fraunhofer.iosb.ilt.frostserver.query.expression.ExpressionVisitor;
import de.fraunhofer.iosb.ilt.frostserver.query.expression.constant.DateTimeConstant;
import de.fraunhofer.iosb.ilt.frostserver.query.expression.constant.IntegerConstant;
import de.fraunhofer.iosb.ilt.frostserver.query.expression.constant.TimeConstant;
import de.fraunhofer.iosb.ilt.frostserver.query.expression.function.Function;
import de.fraunhofer.iosb.ilt.frostserver.query.expression.function.FunctionTypeBinding;
import static net.time4j.tz.ZonalOffset.UTC;

/**
 *
 * @author jab
 */
public class Minute extends Function {

    public Minute() {
        // Parameters added later...
    }

    public Minute(Expression... parameters) {
        super(parameters);
    }

    protected IntegerConstant eval(DateTimeConstant p1) {
        return new IntegerConstant(p1.getValue().toMoment().toZonalTimestamp(UTC).getMinute());
    }

    protected IntegerConstant eval(TimeConstant p1) {
        return new IntegerConstant(p1.getValue().getMinute());
    }

    @Override
    protected void initAllowedTypeBindings() {
        allowedTypeBindings.add(new FunctionTypeBinding(IntegerConstant.class, TimeConstant.class));
        allowedTypeBindings.add(new FunctionTypeBinding(IntegerConstant.class, DateTimeConstant.class));
    }

    @Override
    public <O> O accept(ExpressionVisitor<O> visitor) {
        return visitor.visit(this);
    }

}
