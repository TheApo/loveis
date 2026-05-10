package org.apogames.love.backend.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apogames.love.Constants;
import org.apogames.love.game.MainPanel;
import org.apogames.love.game.love.LoveLevels;
import org.apogames.love.game.userlevels.UserlevelsHelp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.net.HttpRequestBuilder;

public class IOOnlineLibgdx {

	private ArrayList<UserlevelsHelp> userlevels;
	
	private final MainPanel panel;
	
	public IOOnlineLibgdx(MainPanel panel) {
		this.panel = panel;
		userlevels = new ArrayList<UserlevelsHelp>();
	}

	public int getMinID() {
		if (this.userlevels.size() <= 0) {
			return 0;
		}
		return this.userlevels.get(0).getId();
	}

	public ArrayList<UserlevelsHelp> getUserlevels() {
		return userlevels;
	}
	
	private void addRatingForLevel(String level, final int stars, final int fun, final int easy, final int hard, final int creative) {
		for (UserlevelsHelp userlevelsHelp : userlevels) {
			if (userlevelsHelp.getLevel().equals(level)) {
				userlevelsHelp.setCount(userlevelsHelp.getCount() + 1);
				userlevelsHelp.setAll(userlevelsHelp.getAll() + stars);
				userlevelsHelp.setFun(userlevelsHelp.getFun() + fun);
				userlevelsHelp.setEasy(userlevelsHelp.getEasy() + easy);
				userlevelsHelp.setHard(userlevelsHelp.getHard() + hard);
				userlevelsHelp.setCreative(userlevelsHelp.getCreative() + creative);
			}
		}
	}

	public boolean saveLevel(final String level) {
		if (isOriginalLevel(level)) {
			return false;
		}
		try {
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("level", String.valueOf(level));
			HttpRequest request = new HttpRequest(HttpMethods.POST);
			request.setUrl(Constants.USERLEVELS_SAVEPHP);
			request.setHeader("Content-Type", "application/x-www-form-urlencoded");
			request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

			Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
				@Override
				public void handleHttpResponse(HttpResponse httpResponse) {
				}

				@Override
				public void failed(Throwable t) {
					Gdx.app.log("Failed ", t.getMessage());
				}

				@Override
				public void cancelled() {
					Gdx.app.log("Cancelled", "Save cancelled");
				}
			});

			return true;
		} catch (Exception me) {
			System.err.println("Exception: " + me);
		}
		return false;
	}
	
	public boolean saveLevelCount(final String level) {
		try {
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("level", String.valueOf(level));
			HttpRequest request = new HttpRequest(HttpMethods.POST);
			request.setUrl(Constants.USERLEVELS_SAVEPHP_COUNT);
			request.setHeader("Content-Type", "application/x-www-form-urlencoded");
			request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

			Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
				@Override
				public void handleHttpResponse(HttpResponse httpResponse) {
				}

				@Override
				public void failed(Throwable t) {
					Gdx.app.log("Failed ", t.getMessage());
				}

				@Override
				public void cancelled() {
					Gdx.app.log("Cancelled", "Save cancelled");
				}
			});

			return true;
		} catch (Exception me) {
			System.err.println("Exception: " + me);
		}
		return false;
	}
	
	public boolean saveLevelRating(final String level, final int stars, final int fun, final int easy, final int hard, final int creative) {
		addRatingForLevel(level, stars, fun, easy, hard, creative);
		try {
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("level", String.valueOf(level));
			parameters.put("stars", String.valueOf(stars));
			parameters.put("fun", String.valueOf(fun));
			parameters.put("easy", String.valueOf(easy));
			parameters.put("hard", String.valueOf(hard));
			parameters.put("creative", String.valueOf(creative));
			HttpRequest request = new HttpRequest(HttpMethods.POST);
			request.setUrl(Constants.USERLEVELS_SAVEPHP_RATING);
			request.setHeader("Content-Type", "application/x-www-form-urlencoded");
			request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

			Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
				@Override
				public void handleHttpResponse(HttpResponse httpResponse) {
				}

				@Override
				public void failed(Throwable t) {
					Gdx.app.log("Failed ", t.getMessage());
				}

				@Override
				public void cancelled() {
					Gdx.app.log("Cancelled", "Save cancelled");
				}
			});

			return true;
		} catch (Exception me) {
			System.err.println("Exception: " + me);
		}
		return false;
	}

	public boolean loadLevel(int minID) {
		try {
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("minID", String.valueOf(minID));

			HttpRequest request = new HttpRequest(HttpMethods.POST);
			request.setUrl(Constants.USERLEVELS_GETPHP);
			request.setHeader("Content-Type", "application/x-www-form-urlencoded");
			request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

			Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
				@Override
				public void handleHttpResponse(HttpResponse httpResponse) {
					String resultAsString = httpResponse.getResultAsString();
					String[] split = resultAsString.split("\n", -1);
					if ((split != null) && (split.length > 0)) {
						for (int i = 0; i < split.length; i += 11) {
							if (i + 5 < split.length) {
								UserlevelsHelp help = new UserlevelsHelp();
								help.setUserlevelsHelp(split, i);
								if (!isOriginalLevel(help.getLevel())) {
									UserlevelsHelp userlevelsHelp = hasID(help.getId());
									if (userlevelsHelp != null) {
										updateUserlevel(userlevelsHelp, help);
									} else {
										userlevels.add(0, help);
									}
								}
							}
						}
					}
					panel.updateLevelChooser();
				}

				@Override
				public void failed(Throwable t) {
					Gdx.app.log("Failed ", t.getMessage());
					panel.tryAgainLoadLevel();
				}

				@Override
				public void cancelled() {
					Gdx.app.log("Cancelled", "Load cancelled");
				}
			});
			
			return true;
		} catch (Exception me) {
			System.err.println("Exception: " + me);
		}
		return false;
	}

	private void updateUserlevel(UserlevelsHelp userlevelsHelp, UserlevelsHelp newLoad) {
		userlevelsHelp.setCount(newLoad.getCount());
		userlevelsHelp.setFun(newLoad.getFun());
		userlevelsHelp.setEasy(newLoad.getEasy());
		userlevelsHelp.setCreative(newLoad.getCreative());
		userlevelsHelp.setHard(newLoad.getHard());
		userlevelsHelp.setAll(newLoad.getAll());
	}

	private UserlevelsHelp hasID(int id) {
		for (int i = 0; i < userlevels.size(); i++) {
			if (userlevels.get(i).getId() == id) {
				return userlevels.get(i);
			}
		}
		return null;
	}
	
	private boolean isOriginalLevel(String level) {
		for (int i = 0; i < LoveLevels.LEVELS.length; i++) {
			if (LoveLevels.LEVELS[i].equals(level)) {
				return true;
			}
		}
		if (LoveLevels.EDITOR.equals(level)) {
			return true;
		}
		return false;
	}

}
