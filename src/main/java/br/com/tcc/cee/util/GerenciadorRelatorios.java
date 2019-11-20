package br.com.tcc.cee.util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class GerenciadorRelatorios implements Serializable{
	
	
	public byte[] imprimir(List listaDeDados, String arquivoRelatorio, ServletContext ctx) throws Exception {
		JRBeanCollectionDataSource collectDs = new JRBeanCollectionDataSource(listaDeDados);
		String caminhoJasper = ctx.getRealPath("relatorios") + File.separator + arquivoRelatorio +".jasper";
		System.out.println("Caminho: " + caminhoJasper);
		JasperPrint print = JasperFillManager.fillReport(caminhoJasper, new HashMap<>(), collectDs);
		return JasperExportManager.exportReportToPdf(print);
	}

}
