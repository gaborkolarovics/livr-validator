/*
 * Copyright (C) 2020 Gábor KOLÁROVICS
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package livr.validation.rules;

import java.util.List;
import java.util.function.Function;

import livr.FunctionKeeper;
import livr.rules.ExtraRules;
import livr.validation.api.Rule;

/**
 * @author Gábor KOLÁROVICS
 */
public class MongoIdRule implements Rule {

	@Override
	public Function<List<Object>, Function<FunctionKeeper, Object>> func() {
		return ExtraRules.mongo_id;
	}

	@Override
	public String rule() {
		return "mongo_id";
	}

}
