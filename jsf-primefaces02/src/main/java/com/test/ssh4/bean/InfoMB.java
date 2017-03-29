package com.test.ssh4.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import com.test.ssh4.model.Info;
import com.test.ssh4.model.LazyInfoDataModel;
import com.test.ssh4.service.InfoService;

@Named
@SessionScoped
public class InfoMB {
	private LazyDataModel<Info> lazyModel;
	private Info selected = new Info();
	@Inject
	private InfoService infoService;
	
	@PostConstruct
    public void init() {
        lazyModel = new LazyInfoDataModel(infoService.find());
    }

	public void add(ActionEvent e) {
		infoService.save(selected);
		init();
		selected = new Info();
	}

	public void update(ActionEvent e) {
		infoService.update(selected);
		init();
		selected = new Info();
	}

	public void delete(ActionEvent e) {
		infoService.delete(selected);
		init();
		selected = new Info();
	}

	public LazyDataModel<Info> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Info> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Info getSelected() {
		return selected;
	}

	public void setSelected(Info selected) {
		this.selected = selected;
	}
}
